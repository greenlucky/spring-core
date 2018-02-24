package com.devopslam.segapattern.persistance.model;

import com.devopslam.segapattern.persistance.model.inf.ISeqaOrchesSeq;

public class DeliveryInfoSeq implements ISeqaOrchesSeq {

    private ISeqaOrchesSeq previous;
    private ISeqaOrchesSeq next;

    private DeliveryInformation deliveryInformation;

    @Override
    public ISeqaOrchesSeq previous() {
        return previous;
    }

    @Override
    public ISeqaOrchesSeq next() {
        return next;
    }

    public void setPrevious(ISeqaOrchesSeq previous) {
        this.previous = previous;
    }

    public void setNext(ISeqaOrchesSeq next) {
        this.next = next;
    }

    public DeliveryInformation getDeliveryInformation() {
        return deliveryInformation;
    }

    public void setDeliveryInformation(DeliveryInformation deliveryInformation) {
        this.deliveryInformation = deliveryInformation;
    }

    @Override
    public String toString() {
        return "DeliveryInfoSeq{" +
                "previous=" + previous +
                ", next=" + next +
                ", deliveryInformation=" + deliveryInformation +
                '}';
    }
}
