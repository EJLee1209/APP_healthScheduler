package com.example.healthapp

// 사용자 계정 정보 모델 클래스
class UserAccount (){
    private var idToken : String? = null //firebase uid(고유 토큰 정보)
    private var emailId : String? = null // 이메일 아이디
    private var password : String? = null //비밀번호

    fun getIdToken() : String? {return idToken}

    fun setIdToken(idToken : String) {this.idToken = idToken}

    fun getEmailId () : String? {return emailId}

    fun setEmailId (emailId: String?) {this.emailId = emailId}

    fun getPassword () : String? {return password}

    fun setPassword(password : String) {this.password = password}

}