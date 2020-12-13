package basic_operations;

class UpdateThread extends Thread{
        UpdateMsg updateMsg;
        ItemOperations frame;
        public UpdateThread(UpdateMsg updateMsg,ItemOperations frame){
            this.updateMsg=updateMsg;
            this.frame=frame;
        }
        @Override
        public void run() {
            super.run();
            synchronized (updateMsg){
                try {
                    updateMsg.wait();
                    if(updateMsg.isNameUpdated){
                        frame.itemDetails.name=updateMsg.name;
                        frame.itemNameLabel.setText(frame.itemDetails.name);
                    }
                    if(updateMsg.isQuantityUpdated){
                        frame.itemDetails.quantity=updateMsg.quantity;
                        frame.quantityLabel.setText(String.valueOf(frame.itemDetails.quantity));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateMsg.isNameUpdated=false;
                updateMsg.isQuantityUpdated=false;
            }
            synchronized (frame) {
                frame.setEnabled(true);
                frame.setVisible(true);
            }
        }
    }