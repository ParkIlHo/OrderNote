package com.ian.ordernote.db

class DBConfig {

    // db name
    val DB_NAME = "order_note.db"

    // db version
    val DB_VERSION = 1

    // table name
    val TB_ORDER = "order_info"

    // columns

    val CREATE_ORDER = ""
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