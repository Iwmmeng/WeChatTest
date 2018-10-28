package com.wechat.message;

public class Message {
    public String touser;
    public String msgtype;
    public int agentid;
    public int expect;
    public Content text;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public int getAgentid() {
        return agentid;
    }

    public void setAgentid(int agentid) {
        this.agentid = agentid;
    }

    public int getExpect() {
        return expect;
    }

    public void setExpect(int expect) {
        this.expect = expect;
    }

    public Content getText() {
        return text;
    }

    public void setText(Content text) {
        this.text = text;
    }
}
