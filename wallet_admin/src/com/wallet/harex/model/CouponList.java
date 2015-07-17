package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class CouponList extends Common {

	private String cpnId;
    private String cpnName;

    public String getCpnId() {
        return cpnId;
    }

    public void setCpnId(String cpnId) {
        this.cpnId = cpnId == null ? null : cpnId.trim();
    }

    public String getCpnName() {
        return cpnName;
    }

    public void setCpnName(String cpnName) {
        this.cpnName = cpnName == null ? null : cpnName.trim();
    }
}