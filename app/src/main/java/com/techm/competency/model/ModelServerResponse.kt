package com.techm.competency.model

import com.techm.competency.utils.ResponseStatus


/**
 * This class is for handle server response
 * */
class ModelServerResponse(var data:ArrayList<EmployeesResponseModel>, var errorMessage:String="", var status: ResponseStatus)