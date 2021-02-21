package com.jubydull.pt.projecttask.model;

import lombok.Data;

@Data
public class BaseResponse {

    private String successMsg;
    private String errorMsg;
    private int status;
}
