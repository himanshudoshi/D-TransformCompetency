package com.techm.competency.model

import com.techm.competency.utils.ResponseStatus


/**
 * This class for handle Employee response
 * */
class EmployeesResponseModel(
    var error: String,
    var status: ResponseStatus
)