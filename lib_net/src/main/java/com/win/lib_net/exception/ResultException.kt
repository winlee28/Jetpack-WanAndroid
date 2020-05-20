package com.win.lib_net.exception


class ResultException(var errCode: String?, var msg: String?) : Exception(msg)
