
package com.atividadeavaliativa.employeemanagement.logs;


public class GerenciadorStrategy {
   
    private ILogStrategy log;

    public GerenciadorStrategy(ILogStrategy log) {
        this.log = log;
    }
    
    public void setLog(ILogStrategy log){
        this.log=log;
    }
    
    public ILogStrategy getLog(){
        return this.log;
    } 
    
    
}
