package com.hunger.exercise.exception

//改為單例模式
 class  Response private constructor() {
    var isOK = false
    var code = 0
    var message: String? = null
    var data: Any? = null

    companion object {
        @Suppress("不需要private")
        fun success(): Response {
            val res = Response()
            res.isOK=true
            res.code=200
            res.message="請求響應成功"
            return res
        }

        fun success(obj: Any?): Response {
            val res:Response = success()
            res.data=obj
            return res
        }

        //全局異常
        fun fail(exception: CustomException): Response {
            val res = Response()
            res.isOK=false
            res.code=200
            res.message="請求響應失敗"
            return res
        }

        fun fail(exception: CustomException,message:String): Response {
            val res = Response()
            res.isOK=false
            res.code=200
            res.message=message
            return res
        }
    }
}