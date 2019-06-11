
package process.matchData;

/**
 * @author roman
 */
public class Bean {
    private String custFnName;
    private String agentFnName;
    private String cusStatus; // 'O' for Old and 'S' for Standard
  
    public String getCustFnName() {
        return custFnName;
    }

    public void setCustFnName(String custFnName) {
        this.custFnName = custFnName;
    }
    
    public String getAgentFnName() {
        return agentFnName;
    }

    public void setAgentFnName(String agentFnName) {
        this.agentFnName = agentFnName;
    }
    
    public String getCusStatus() {
        return cusStatus;
    }

    public void setCusStatus(String cusStatus) {
        this.cusStatus = cusStatus;
    }

}
