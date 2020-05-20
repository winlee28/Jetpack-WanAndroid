package com.win.lib_net.exception

object ApiResultCode {

    //对应HTTP的状态码
    const val UNAUTHORIZED = 401
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val REQUEST_TIMEOUT = 408
    const val INTERNAL_SERVER_ERROR = 500
    const val BAD_GATEWAY = 502
    const val SERVICE_UNAVAILABLE = 503
    const val GATEWAY_TIMEOUT = 504

    const val RESULT_NORMAL = 0
    const val UNKNOWN = "1000"
    const val PARSE_ERROR = "1001"
    const val NETWORK_NOTCONNECTION = "1002"
    const val UNKNOW_HOST = "1003"
    const val GW_ERROR = "1004"
    const val ARGS_ERROR = "1005"
    const val TOKEN_ERROR = "1006"
    const val SIGN_ERROR = "1007"

    /**
     * 网关错误
     */
    const val USER_INVALID = "2001"
    const val SECRET_KEY_INVALID = "2002"
    const val TIME_INVALID = "2003"

    const val SSL_ERROR = "3001"
    const val API_RESULT_NORMAL = "000000"

    const val DATA_NULL = "201"

    const val CANCEL = "Multipart Cancel"


}
