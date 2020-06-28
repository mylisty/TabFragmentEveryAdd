package com.liandongfenqi.tongniu.tabfragmenteveryadd.ui;

import java.util.List;

public class Bean {

    /**
     * totalQuantity : 1
     * items : [{"quantity":1,"erpImportName":null,"barcodeItems":["B0W6F12792"]}]
     */

    private int totalQuantity;
    private List<ItemsBean> items;

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * quantity : 1
         * erpImportName : null
         * barcodeItems : ["B0W6F12792"]
         */

        private int quantity;
        private String erpImportName;
        private List<String> barcodeItems;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getErpImportName() {
            return erpImportName;
        }

        public void setErpImportName(String erpImportName) {
            this.erpImportName = erpImportName;
        }

        public List<String> getBarcodeItems() {
            return barcodeItems;
        }

        public void setBarcodeItems(List<String> barcodeItems) {
            this.barcodeItems = barcodeItems;
        }
    }
}
