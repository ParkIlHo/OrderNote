package com.ian.ordernote.db

class DBConfig {

    // db name
    val DB_NAME = "order_note.db"

    // db version
    val DB_VERSION = 1

    // table name
    val TB_CUSTOMER = "customer_info"
    val TB_ORDER = "order_info"

    // columns
    // name
    val CO_NAME = "name"
    // 이메일
    val CO_EMAIL = "email"
    // 전화번호
    val CO_TEL = "tel"
    // 핸드폰
    val CO_MOBILE = "mobile"

    //상품명
    val CO_PRODUCT_NAME = "product_name"
    //주문날짜
    val CO_ORDER_DATE = "order_date"
    //출고예정일
    val CO_RELEASE_SCHEDULE = "release_schedule"
    //원가
    val CO_COAST_PRICE = "coast_price"
    //판매금액
    val CO_SELLING_PRICE = "selling_price"
    //출고완료여부
    val CO_RELEASE_YN = "release_yn"
    //상품이미지
    val CO_PRODUCT_IMAGE = "product_image"
    //배송주소
    val CO_SHIPPING_ADDRESS = "shipping_address"
    //거래처명
    val CO_ACCOUNT_NAME = "account_name"
    //함량
    val CO_CONTENT = "content"
    //컬러
    val CO_COLOR = "color"
    //사이즈
    val CO_SIZE = "size"
    //변형
    val CO_TRANSFORM = "transform"
    //손님약속날짜
    val CO_PROMISE_DATE = "promise_date"
    //비고
    val CO_OTHER = "other"
    val CO_INDEX = "id"


    val CREATE_CUSTOMER = "CREATE TABLE IF NOT EXISTS " + TB_CUSTOMER +
            " (" + CO_INDEX + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CO_NAME + " VARCHAR(20) NOT NULL," +
            CO_EMAIL + " VARCHAR(100)," +
            CO_TEL + " VARCHAR(20)," +
            CO_MOBILE + " VARCHAR(20) NOT NULL," +
            CO_OTHER + " VARCHAR(500))"
    val CREATE_ORDER = "CREATE TABLE IF NOT EXISTS " + TB_ORDER +
//            " (" + CO_INDEX + " INT NOT NULL AUTO_INCREMENT," +
//            " (" + CO_INDEX + " INT NOT NULL AUTOINCREMENT," +
            " (" + CO_INDEX + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CO_NAME + " VARCHAR(20) NOT NULL," +
            CO_EMAIL + " VARCHAR(100)," +
            CO_TEL + " VARCHAR(20)," +
            CO_MOBILE + " VARCHAR(20) NOT NULL," +

            CO_PRODUCT_NAME + " VARCHAR(20) NOT NULL," +
            CO_ORDER_DATE + " DATETIME," +
            CO_RELEASE_SCHEDULE + " DATETIME," +
            CO_COAST_PRICE + " INT UNSIGNED NOT NULL DEFAULT '0'," +
            CO_SELLING_PRICE + " INT UNSIGNED NOT NULL DEFAULT '0'," +
            CO_RELEASE_YN + " CHAR(1) NOT NULL DEFAULT 'N'," +
            CO_PRODUCT_IMAGE + " VARCHAR(500)," +
            CO_SHIPPING_ADDRESS + " VARCHAR(500)," +
            CO_ACCOUNT_NAME + " VARCHAR(20)," +
            CO_CONTENT + " INT UNSIGNED NOT NULL DEFAULT '0'," +
            CO_COLOR + " VARCHAR(20)," +
            CO_SIZE + " VARCHAR(50)," +
            CO_TRANSFORM + " VARCHAR(500)," +
            CO_PROMISE_DATE + " DATETIME," +
            CO_OTHER + " VARCHAR(500))"
//            CO_OTHER + " VARCHAR(500)," +
//            " Primary Key(" + CO_INDEX + ")" + ");"
    /*val CREATE_ORDER = "CREATE TABLE IF NOT EXISTS " + TB_ORDER +
            "(" + CO_STD_ID + " INT NOT NULL," +
            CO_NAME + " VARCHAR(20) NOT NULL," +
            CO_SCHOOL + " VARCHAR(20) NOT NULL," +
            CO_YEAR + " CHAR(1) NOT NULL DEFAULT 1," +
            CO_SUB_1 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_2 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_3 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_4 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_5 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_6 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_7 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_8 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_9 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_10 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_11 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_12 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_13 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_14 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_15 + " CHAR(1) NOT NULL DEFAULT N," +
            CO_SUB_16 + " CHAR(1) NOT NULL DEFAULT N," +
//            CO_DATE + " DATETIME," +
            CO_DATE + " VARCHAR(20) NOT NULL," +
            CO_STD_PHONE + " VARCHAR(20)," +
            CO_PAR_PHONE + " VARCHAR(20)," +
            CO_OTHER + " VARCHAR(500)," +
            " Primary Key(" + CO_STD_ID + ")" + ");";*/

    /*// 4. remane 테이블 삭제
    val TEMP_DROP_RENAMECOMINFO = "drop table _comInfo"


    // 14 봄학기 ComInfo Free 데이터 컬럼 추가
    val ALERT_COMINFO_COMFREEYN = "ALTER TABLE $TB_COMINFO ADD COLUMN $CO_COM_FREEYN char(1) DEFAULT 'N' NOT NULL"
    val TEMP_UPDATE_ISFREEYN = "UPDATE $TB_COMINFO set $CO_COM_FREEYN='Y' WHERE $CO_COM_ID IN ('697','698','699') "

    // 14 가을학기 LessonInfo cjrn_lesson_no 데이터 컬럼 추가
    val ALERT_LESSONINFO_CJRNLESSONNO =
        "ALTER TABLE $TB_LESSONINFO ADD COLUMN $CO_CJRN_LESSON_NO INT DEFAULT 1 NOT NULL"

    // 14 겨울학기 LessonInfo extra_point 데이터 컬럼 추가
    val ALERT_LESSONINFO_EXTRAPOINT = "ALTER TABLE $TB_LESSONINFO ADD COLUMN $CO_EXTRA_POINT INT DEFAULT 0 NOT NULL"

    // 15 여름학기 LessonInfo tempSubmitYN 데이터 컬럼 추가
    val ALERT_LESSONINFO_TEMPSUBMITYN =
        "ALTER TABLE $TB_LESSONINFO ADD COLUMN $CO_TEMPSUBMITYN char(1) DEFAULT 'N' NOT NULL"

    // 15 여름학기 LessonInfo tempSubmitEnable 데이터 컬럼 추가
    val ALERT_LESSONINFO_TEMPSUBMITENABLE =
        "ALTER TABLE $TB_LESSONINFO ADD COLUMN $CO_TEMPSUBMITENABLE INT DEFAULT 0 NOT NULL"

    // 16 겨울 학기 ComInfo evalFeedbfackYN 데이터 컬럼 추가
    val ALERT_COMINFO_EVALFEEDBACKYN = "ALTER TABLE $TB_COMINFO ADD COLUMN $CO_EVAL_FEEDBACK_YN char(1) DEFAULT 'N'"

    // 17 봄학기 LessonInfo is_display_portfolio 데이터 컬럼 추가
    val ALERT_LESSONINFO_PORTFOLIO = "ALTER TABLE $TB_LESSONINFO ADD COLUMN $CO_IS_PORTFOLIO INT DEFAULT 0 NOT NULL"*/

}