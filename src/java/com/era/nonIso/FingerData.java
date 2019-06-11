/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.nonIso;

import com.futronic.SDKHelper.FtrIdentifyRecord;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class FingerData {

    private String m_UserName;
    private byte[] m_Key;
    private byte[] m_Template;

    public FingerData() {

    }

    public FingerData(String m_UserName, byte[] m_Key, byte[] m_Template) {
        this.m_UserName = m_UserName;
        this.m_Key = m_Key;
        this.m_Template = m_Template;
    }

    public String getM_UserName() {
        return m_UserName;
    }

    public void setM_UserName(String m_UserName) {
        this.m_UserName = m_UserName;
    }

    public byte[] getM_Key() {
        return m_Key;
    }

    public void setM_Key(byte[] m_Key) {
        this.m_Key = m_Key;
    }

    public byte[] getM_Template() {
        return m_Template;
    }

    public void setM_Template(byte[] m_Template) {
        this.m_Template = m_Template;
    }

    public FtrIdentifyRecord getFtrIdentifyRecord() {
        FtrIdentifyRecord r = new FtrIdentifyRecord();
        r.m_KeyValue = m_Key;
        r.m_Template = m_Template;
        return r;
    }

}
