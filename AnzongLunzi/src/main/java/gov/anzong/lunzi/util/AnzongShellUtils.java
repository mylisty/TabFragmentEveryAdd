package gov.anzong.lunzi.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Anh Lai on 2016/9/20 0020.
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖保佑             永无BUG
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 */
public class AnzongShellUtils {

    public static final String COMMAND_SU       = "su";
    public static final String COMMAND_SH       = "sh";
    public static final String COMMAND_EXIT     = "exit\n";
    public static final String COMMAND_LINE_END = "\n";

    private AnzongShellUtils() {
        throw new AssertionError();
    }

    /**
     * check whether has root permission
     *
     * @return
     */
    public static boolean checkRootPermission() {
        return execCommand("echo root", true, false).result == 0;
    }

    /**
     * execute shell command, default return result msg
     *
     * @param command command
     * @param isRoot whether need to run with root
     * @return
     * @see AnzongShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execCommand(String command, boolean isRoot) {
        return execCommand(new String[] {command}, isRoot, true);
    }

    /**
     * execute shell commands, default return result msg
     *
     * @param commands command list
     * @param isRoot whether need to run with root
     * @return
     * @see AnzongShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execCommand(List<String> commands, boolean isRoot) {
        return execCommand(commands == null ? null : commands.toArray(new String[] {}), isRoot, true);
    }

    /**
     * execute shell commands, default return result msg
     *
     * @param commands command array
     * @param isRoot whether need to run with root
     * @return
     * @see AnzongShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execCommand(String[] commands, boolean isRoot) {
        return execCommand(commands, isRoot, true);
    }

    /**
     * execute shell command
     *
     * @param command command
     * @param isRoot whether need to run with root
     * @param isNeedResultMsg whether need result msg
     * @return
     * @see AnzongShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execCommand(String command, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(new String[] {command}, isRoot, isNeedResultMsg);
    }

    /**
     * execute shell commands
     *
     * @param commands command list
     * @param isRoot whether need to run with root
     * @param isNeedResultMsg whether need result msg
     * @return
     * @see AnzongShellUtils#execCommand(String[], boolean, boolean)
     */
    public static CommandResult execCommand(List<String> commands, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(commands == null ? null : commands.toArray(new String[] {}), isRoot, isNeedResultMsg);
    }

    /**
     * execute shell commands
     *
     * @param commands command array
     * @param isRoot whether need to run with root
     * @param isNeedResultMsg whether need result msg
     * @return <ul>
     *         <li>if isNeedResultMsg is false, {@link CommandResult#successMsg} is null and
     *         {@link CommandResult#errorMsg} is null.</li>
     *         <li>if {@link CommandResult#result} is -1, there maybe some excepiton.</li>
     *         </ul>
     */
    public static CommandResult execCommand(String[] commands, boolean isRoot, boolean isNeedResultMsg) {
        int result = -1;
        if (commands == null || commands.length == 0) {
            return new CommandResult(result, null, null);
        }

        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;

        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec(isRoot ? COMMAND_SU : COMMAND_SH);
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) {
                    continue;
                }

                // donnot use os.writeBytes(commmand), avoid chinese charset error
                os.write(command.getBytes());
                os.writeBytes(COMMAND_LINE_END);
                os.flush();
            }
            os.writeBytes(COMMAND_EXIT);
            os.flush();

            result = process.waitFor();
            // get command result
            if (isNeedResultMsg) {
                successMsg = new StringBuilder();
                errorMsg = new StringBuilder();
                successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
                errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String s;
                while ((s = successResult.readLine()) != null) {
                    successMsg.append(s);
                }
                while ((s = errorResult.readLine()) != null) {
                    errorMsg.append(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (process != null) {
                process.destroy();
            }
        }
        return new CommandResult(result, successMsg == null ? null : successMsg.toString(), errorMsg == null ? null
                : errorMsg.toString());
    }

    /**
     * result of command
     * <ul>
     * <li>{@link CommandResult#result} means result of command, 0 means normal, else means error, same to excute in
     * linux shell</li>
     * <li>{@link CommandResult#successMsg} means success message of command result</li>
     * <li>{@link CommandResult#errorMsg} means error message of command result</li>
     * </ul>
     *
     * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-5-16
     */
    public static class CommandResult {

        /** result of command **/
        public int    result;
        /** success message of command result **/
        public String successMsg;
        /** error message of command result **/
        public String errorMsg;

        public CommandResult(int result) {
            this.result = result;
        }

        public CommandResult(int result, String successMsg, String errorMsg) {
            this.result = result;
            this.successMsg = successMsg;
            this.errorMsg = errorMsg;
        }
    }
}