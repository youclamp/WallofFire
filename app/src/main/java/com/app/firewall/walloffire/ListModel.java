package com.app.firewall.walloffire;

/**
 * Created by Tyrell on 5/3/2015.
 */
public class ListModel {

    private String rule_no = "";
    private String target = "";
    private String protocol = "";
    private String interfaces = "";
    private String port = "";
    private String ip = "";
    private String chain = "";
    private String target2 = "";

    //get methods
    public String getRule_no()
    {
        return this.rule_no;
    }

    public String getTarget()
    {
       return this.target;
    }

    public String getProtocol()
    {
        return this.protocol;
    }

    public String getInterfaces()
    {
        return this.interfaces;
    }

    public String getPort()
    {
       return this.port;
    }

    public String getIp()
    {
        return this.ip;
    }

    public String getChain()
    {
        return this.chain;
    }

    public String getTarget2()
    {
        return this.target2;
    }

    //set methods
    public void setRule_no(String rule_no)
    {
        this.rule_no = rule_no;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public void setProtocol(String protocol)
    {
        this.protocol = protocol;
    }

    public void setInterfaces(String interfaces)
    {
        this.interfaces = interfaces;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public void setChain(String chain)
    {
        this.chain = chain;
    }

    public void setTarget2(String target2)
    {
        this.target2 = target2;
    }
}
