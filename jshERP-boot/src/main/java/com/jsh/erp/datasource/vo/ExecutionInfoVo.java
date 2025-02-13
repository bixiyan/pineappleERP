package com.jsh.erp.datasource.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExecutionInfoVo {
    private List<ExecutionInfoVoItem> inExecution;
    private List<ExecutionInfoVoItem> unExecution;

    public ExecutionInfoVo(){
        this.inExecution = new ArrayList<>();
        this.unExecution = new ArrayList<>();
    }

    public void addInExecution(ExecutionInfoVoItem item){
        this.inExecution.add(item);
    }

    public void addUnExecution(ExecutionInfoVoItem item){
        this.unExecution.add(item);
    }

    public List<ExecutionInfoVoItem> getInExecution() {
        return inExecution;
    }

    public void setInExecution(final List<ExecutionInfoVoItem> inExecution) {
        this.inExecution = inExecution;
    }

    public List<ExecutionInfoVoItem> getUnExecution() {
        return unExecution;
    }

    public void setUnExecution(final List<ExecutionInfoVoItem> unExecution) {
        this.unExecution = unExecution;
    }

    public static class ExecutionInfoVoItem{
        private String designer;
        private String organ;

        public ExecutionInfoVoItem(String designer,String organ){
            this.designer = designer;
            this.organ = organ;
        }

        public String getDesigner() {
            return designer;
        }

        public void setDesigner(final String designer) {
            this.designer = designer;
        }

        public String getOrgan() {
            return organ;
        }

        public void setOrgan(final String organ) {
            this.organ = organ;
        }
    }

}
