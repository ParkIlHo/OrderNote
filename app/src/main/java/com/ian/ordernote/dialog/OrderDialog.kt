package com.ian.ordernote.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ian.ordernote.Const
import com.ian.ordernote.CustomerListActivity
import com.ian.ordernote.OrderListActivity
import com.ian.ordernote.R
import com.ian.ordernote.core.CommonAlertDialog
import com.ian.ordernote.data.CustomerInfo
import com.ian.ordernote.data.OrderInfo
import com.ian.ordernote.db.DB
import org.w3c.dom.Text
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class OrderDialog(context: Context?, listener: OrderListActivity.OrderInfoListener) : CommonAlertDialog(context), View.OnClickListener, View.OnTouchListener, CompoundButton.OnCheckedChangeListener {

    var isAdd = true
    var isChange = false
    lateinit var mOrderInfo: OrderInfo
    lateinit var mTempOrderInfo: OrderInfo

    lateinit var mContext: Context
    lateinit var mBuilder: AlertDialog.Builder
    lateinit var mDialogView: View
    lateinit var mListener: OrderListActivity.OrderInfoListener

    var mDb : DB? = null

    var datePickerMode = -1

    val DATE_PICKER_ORDER   = 0
    val DATE_PICKER_RELEASE = 1
    val DATE_PICKER_PROMISE = 2

    var datePickerDialog: DatePickerDialog? = null

    var mProductImageUri: Uri? = null

    lateinit var mNameEdit: EditText
    lateinit var mTelEdit: EditText
    lateinit var mEmailEdit: EditText
    lateinit var mProductNameEdit: EditText
    lateinit var mOrderDateEdit: EditText
    lateinit var mReleaseDateEdit: EditText
    lateinit var mCoastPriceEdit: EditText
    lateinit var mSellingPriceEdit: EditText
    lateinit var mReleaseEdit: EditText
    lateinit var mReleaseLayout: RadioGroup
    lateinit var mReleaseY: RadioButton
    lateinit var mReleaseN: RadioButton
//    lateinit var mProduceImageEdit: EditText
    lateinit var mShippingAddressEdit: EditText
    lateinit var mAccountNameEdit: EditText
//    lateinit var mContentsEdit: EditText
    lateinit var mColorEdit: EditText
    lateinit var mSizeEdit: EditText
    lateinit var mTransformEdit: EditText
    lateinit var mPromiseDateEdit: EditText
    lateinit var mOtherEdit: EditText

    lateinit var mNameText: TextView
    lateinit var mTelText: TextView
    lateinit var mEmailText: TextView
    lateinit var mProductNameText: TextView
    lateinit var mOrderDateText: TextView
    lateinit var mReleaseDateText: TextView
    lateinit var mCoastPriceText: TextView
    lateinit var mSellingPriceText: TextView
    lateinit var mReleaseText: TextView
//    lateinit var mProduceImageText: TextView
    lateinit var mShippingAddressText: TextView
    lateinit var mAccountNameText: TextView
//    lateinit var mContentsText: TextView
    lateinit var mColorText: TextView
    lateinit var mSizeText: TextView
    lateinit var mTransformText: TextView
    lateinit var mPromiseDateText: TextView
    lateinit var mOtherText: TextView

    lateinit var mProductImage: ImageView

    lateinit var mAccountBtn: Button

    lateinit var mContent14K: RadioButton
    lateinit var mContent18K: RadioButton
    lateinit var mContent24K: RadioButton
    lateinit var mContentOther: RadioButton

    lateinit var mContentOtherLayout: ViewGroup
    lateinit var mContentOtherEdit: EditText
    lateinit var mContentOtherText: TextView

    init {
        mDb = DB(context).getInstance(context!!)
        mListener = listener

        mContext = context!!
        mDialogView = layoutInflater.inflate(R.layout.dialog_add_order, null)
        setView(mDialogView)

        mNameEdit = mDialogView.findViewById(R.id.dialog_add_order_name)
        mTelEdit = mDialogView.findViewById(R.id.dialog_add_order_tel)
        mEmailEdit = mDialogView.findViewById(R.id.dialog_add_order_email)
        mProductNameEdit = mDialogView.findViewById(R.id.dialog_add_order_product_name)
        mOrderDateEdit = mDialogView.findViewById(R.id.dialog_add_order_date)
        mReleaseDateEdit = mDialogView.findViewById(R.id.dialog_add_order_release_schedule)
        mCoastPriceEdit = mDialogView.findViewById(R.id.dialog_add_order_coast_price)
        mSellingPriceEdit = mDialogView.findViewById(R.id.dialog_add_order_selling_price)
        mReleaseEdit = mDialogView.findViewById(R.id.dialog_add_order_release)
        mReleaseLayout = mDialogView.findViewById(R.id.dialog_add_order_release_layout)
        mReleaseY = mDialogView.findViewById(R.id.dialog_add_order_release_y)
        mReleaseN = mDialogView.findViewById(R.id.dialog_add_order_release_n)
        mShippingAddressEdit = mDialogView.findViewById(R.id.dialog_add_order_shipping_address)
        mAccountNameEdit = mDialogView.findViewById(R.id.dialog_add_order_account_name)
//        mContentsEdit = mDialogView.findViewById(R.id.dialog_add_order_content)
        mColorEdit = mDialogView.findViewById(R.id.dialog_add_order_color)
        mSizeEdit = mDialogView.findViewById(R.id.dialog_add_order_size)
        mTransformEdit = mDialogView.findViewById(R.id.dialog_add_order_transform)
        mPromiseDateEdit = mDialogView.findViewById(R.id.dialog_add_order_promise_date)
        mOtherEdit = mDialogView.findViewById(R.id.dialog_add_order_other)

        mNameText = mDialogView.findViewById(R.id.dialog_add_order_name_text)
        mTelText = mDialogView.findViewById(R.id.dialog_add_order_tel_text)
        mEmailText = mDialogView.findViewById(R.id.dialog_add_order_email_text)
        mProductNameText = mDialogView.findViewById(R.id.dialog_add_order_product_name_text)
        mOrderDateText = mDialogView.findViewById(R.id.dialog_add_order_date_text)
        mReleaseDateText = mDialogView.findViewById(R.id.dialog_add_order_release_schedule_text)
        mCoastPriceText = mDialogView.findViewById(R.id.dialog_add_order_coast_price_text)
        mSellingPriceText = mDialogView.findViewById(R.id.dialog_add_order_selling_price_text)
        mReleaseText = mDialogView.findViewById(R.id.dialog_add_order_release_text)
        mShippingAddressText = mDialogView.findViewById(R.id.dialog_add_order_shipping_address_text)
        mAccountNameText = mDialogView.findViewById(R.id.dialog_add_order_account_name_text)
//        mContentsText = mDialogView.findViewById(R.id.dialog_add_order_content_text)
        mColorText = mDialogView.findViewById(R.id.dialog_add_order_color_text)
        mSizeText = mDialogView.findViewById(R.id.dialog_add_order_size_text)
        mTransformText = mDialogView.findViewById(R.id.dialog_add_order_transform_text)
        mPromiseDateText = mDialogView.findViewById(R.id.dialog_add_order_promise_date_text)
        mOtherText = mDialogView.findViewById(R.id.dialog_add_order_other_text)

        mAccountBtn = mDialogView.findViewById(R.id.dialog_add_order_account_btn)

        mProductImage = mDialogView.findViewById<ImageView>(R.id.dialog_add_order_product_image_view)

        mContent14K = mDialogView.findViewById(R.id.dialog_add_order_content_14)
        mContent18K = mDialogView.findViewById(R.id.dialog_add_order_content_18)
        mContent24K = mDialogView.findViewById(R.id.dialog_add_order_content_24)
        mContentOther = mDialogView.findViewById(R.id.dialog_add_order_content_other)

        mContentOtherLayout = mDialogView.findViewById(R.id.dialog_add_order_content_other_layout)
        mContentOtherEdit = mDialogView.findViewById(R.id.dialog_add_order_content_other_edit)
        mContentOtherText = mDialogView.findViewById(R.id.dialog_add_order_content_other_text)

        mDialogView.findViewById<Button>(R.id.dialog_add_order_cancel_btn).setOnClickListener(this)
        mDialogView.findViewById<Button>(R.id.dialog_add_order_save_btn).setOnClickListener(this)
        mDialogView.findViewById<Button>(R.id.dialog_add_order_confirm_btn).setOnClickListener(this)
        mDialogView.findViewById<Button>(R.id.dialog_add_order_change_btn).setOnClickListener(this)
        mProductImage.setOnClickListener(this)

        mAccountBtn.setOnClickListener(this)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mOrderDateEdit.showSoftInputOnFocus = false
            mOrderDateEdit.showSoftInputOnFocus = false
            mOrderDateEdit.showSoftInputOnFocus = false
        }

        mOrderDateEdit.setOnTouchListener(this)
        mReleaseDateEdit.setOnTouchListener(this)
        mPromiseDateEdit.setOnTouchListener(this)

        mContent14K.setOnCheckedChangeListener(this)
        mContent18K.setOnCheckedChangeListener(this)
        mContent24K.setOnCheckedChangeListener(this)
        mContentOther.setOnCheckedChangeListener(this)

        mReleaseN.setOnCheckedChangeListener { view, isChecked ->
            if(isChecked) {
                mOrderInfo.releaseYN = "N"
            }
        }

        mReleaseY.setOnCheckedChangeListener { view, isChecked ->
            if(isChecked) {
                mOrderInfo.releaseYN = "Y"
            }
        }

        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    override fun dismiss() {
        super.dismiss()
        Log.e("151515", "OrderDialog dismiss")
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.dialog_add_order_cancel_btn -> { // Add cancel
                if(isAdd) {

                    showAlertDialog(mContext, "작성 중이던 내역을 취소하시겠습니까? 작성중이던 내용은 저장되지 않습니다.",
                        mContext.getString(R.string.confirm),
                        mContext.getString(R.string.cancel),
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            if (isShowing) {
                                dismiss()
                            }
                        },
                        null)
                } else {
                    // 수정중인 order info cancel popup
                    showAlertDialog(mContext, "주문내역 수정을 취소하시겠습니까? 수정중이던 내용은 저장되지 않습니다.",
                        mContext.getString(R.string.confirm),
                        mContext.getString(R.string.cancel),
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            setOrderInfoText(mOrderInfo)
                            setMode(false)
                        },
                        null)
                }
            }

            R.id.dialog_add_order_save_btn -> { // Add 저장

                if(TextUtils.isEmpty(mNameEdit.text)
                    || TextUtils.isEmpty(mTelEdit.text)
                    || TextUtils.isEmpty(mProductNameEdit.text)) {
                    showAlertDialog(mContext,
                        mContext.getString(R.string.order_check_msg),
                        mContext.getString(R.string.confirm),
                        null)
                } else {
                    if(saveOrderInfo() > -1) {
                        if (mListener != null) {
                            mListener.onAdd()
                        }
                        if(isAdd) {
                            if (isShowing) {
                                dismiss()
                            }
                        } else {
                            // edit mode -> 확인 모드로 전환
                            setOrderInfoText(mOrderInfo)
                            setMode(false)
                        }
                    }
                }
            }

            R.id.dialog_add_order_change_btn -> { // 수정 버튼
                mTempOrderInfo = mOrderInfo

                setOrderInfoEdit(mTempOrderInfo)
                setMode(true)
            }

            R.id.dialog_add_order_confirm_btn -> { // 확인 버튼
                if (isShowing) {
                    dismiss()
                }
            }

            R.id.dialog_add_order_account_btn -> { // add account button
                // 사용자 검색 기능 추가
                mListener?.let {
                    it.showCustomerSelect()
                }
            }

            R.id.dialog_add_order_product_image_view -> {
                if (isAdd || isChange) {
                    // 앨범 호출 하여 image 추가
                    selectGallerty()
                } else {
                    // popup 창으로 image 보여짐
                }
            }

            R.id.dialog_add_order_content_14 -> {

            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        when(buttonView?.id) {
            R.id.dialog_add_order_content_14 -> {
                if(isChecked) {
                    mOrderInfo.content = 0
                    mOrderInfo.content_other = ""
                    mContentOtherEdit.setText("")
                    mContentOtherLayout.visibility = View.GONE
                }
            }

            R.id.dialog_add_order_content_18 -> {
                if(isChecked) {
                    mOrderInfo.content = 1
                    mOrderInfo.content_other = ""
                    mContentOtherEdit.setText("")
                    mContentOtherLayout.visibility = View.GONE
                }
            }

            R.id.dialog_add_order_content_24 -> {
                if(isChecked) {
                    mOrderInfo.content = 0
                    mOrderInfo.content_other = ""
                    mContentOtherEdit.setText("")
                    mContentOtherLayout.visibility = View.GONE
                }
            }

            R.id.dialog_add_order_content_other -> {
                if(isChecked) {
                    mOrderInfo.content = 4
                    mContentOtherEdit.setText("")
                    mContentOtherLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        when (motionEvent?.action) {
            MotionEvent.ACTION_DOWN -> {

            }

            MotionEvent.ACTION_UP -> {
                when (view?.id) {
                    R.id.dialog_add_order_date -> { // 주문 날짜 클릭
                        datePickerMode = DATE_PICKER_ORDER

                        showDatePicker()
                    }

                    R.id.dialog_add_order_release_schedule -> { // 출고 날짜 클릭
                        datePickerMode = DATE_PICKER_RELEASE

                        showDatePicker()
                    }

                    R.id.dialog_add_order_promise_date -> { // 약속 날짜 클릭
                        datePickerMode = DATE_PICKER_PROMISE

                        showDatePicker()
                    }
                }
            }
        }
        return true
    }

    override fun show() {
        super.show()

        if(isAdd) {
            mOrderInfo = OrderInfo()
            mTempOrderInfo = OrderInfo()
            init()
        } else {
            this.isChange = false
            mTempOrderInfo = mOrderInfo
            setOrderInfoText(mOrderInfo)
            mDialogView.findViewById<TextView>(R.id.dialog_add_order_title).setText(R.string.confirm_order)
        }
        setMode(isAdd)
    }

    fun init() {
        mNameEdit.setText("")
        mEmailEdit.setText("")
        mTelEdit.setText("")

        mProductNameEdit.setText("")
        mOrderDateEdit.setText("")
        mReleaseDateEdit.setText("")
        mCoastPriceEdit.setText("")
        mSellingPriceEdit.setText("")
        mReleaseEdit.setText("")
        if(mOrderInfo == null || TextUtils.isEmpty(mOrderInfo.releaseYN)) {

            mReleaseN.isChecked = true
        } else {
            if(mOrderInfo.releaseYN.equals("Y")) {
                mReleaseY.isChecked = true
            } else {
                mReleaseN.isChecked = true
            }
        }
//        mProduceImageEdit.setText("")
        mShippingAddressEdit.setText("")
        mAccountNameEdit.setText("")
//        mContentsEdit.setText("")

        if(mOrderInfo == null || mOrderInfo.content < 0) {
            mContent14K.isChecked = true
        } else {
            when(mOrderInfo.content) {
                Const.ORDER_CONTENT_14K -> {
                    mContent14K.isChecked = true
                    mOrderInfo.content_other = ""
                    mContentOtherEdit.setText("")
                    mContentOtherLayout.visibility = View.GONE
                }
                Const.ORDER_CONTENT_18K -> {
                    mContent18K.isChecked = true
                    mOrderInfo.content_other = ""
                    mContentOtherEdit.setText("")
                    mContentOtherLayout.visibility = View.GONE
                }
                Const.ORDER_CONTENT_24K -> {
                    mContent24K.isChecked = true
                    mOrderInfo.content_other = ""
                    mContentOtherEdit.setText("")
                    mContentOtherLayout.visibility = View.GONE
                }
                Const.ORDER_CONTENT_OTHER -> {
                    mContentOther.isChecked = true
                    mContentOtherLayout.visibility = View.VISIBLE
                    mContentOtherEdit.setText(mOrderInfo.content_other)
                }
            }
        }

        mColorEdit.setText("")
        mSizeEdit.setText("")
        mTransformEdit.setText("")
        mPromiseDateEdit.setText("")
        mOtherEdit.setText("")
    }

    fun setOrderInfo(orderInfo: OrderInfo) {
        mOrderInfo = orderInfo
    }

    private fun setOrderInfoEdit(orderInfo: OrderInfo) {
        mNameEdit.setText(orderInfo.name)
        mEmailEdit.setText(orderInfo.email)
        mTelEdit.setText(orderInfo.tel)

        mProductNameEdit.setText(orderInfo.productName)
        mOrderDateEdit.setText(orderInfo.orderDate)
        mReleaseDateEdit.setText(orderInfo.releaseSchedule)
        mCoastPriceEdit.setText(orderInfo.coastPrice)
        mSellingPriceEdit.setText(orderInfo.sellingPrice)
        mReleaseEdit.setText(orderInfo.releaseYN)
        if(mOrderInfo == null || TextUtils.isEmpty(mOrderInfo.releaseYN)) {
            mReleaseN.isChecked = true
        } else {
            if(mOrderInfo.releaseYN.equals("Y")) {
                mReleaseY.isChecked = true
            } else {
                mReleaseN.isChecked = true
            }
        }
//        mProduceImageEdit.setText(orderInfo.productImage)
        mShippingAddressEdit.setText(orderInfo.shippingAddress)
        mAccountNameEdit.setText(orderInfo.accountName)
//        mContentsEdit.setText(orderInfo.content)

        when(mOrderInfo.content) {
            Const.ORDER_CONTENT_14K -> {
                mContent14K.isChecked = true
                mOrderInfo.content_other = ""
                mContentOtherEdit.setText("")
                mContentOtherLayout.visibility = View.GONE
            }
            Const.ORDER_CONTENT_18K -> {
                mContent18K.isChecked = true
                mOrderInfo.content_other = ""
                mContentOtherEdit.setText("")
                mContentOtherLayout.visibility = View.GONE
            }
            Const.ORDER_CONTENT_24K -> {
                mContent24K.isChecked = true
                mOrderInfo.content_other = ""
                mContentOtherEdit.setText("")
                mContentOtherLayout.visibility = View.GONE
            }
            Const.ORDER_CONTENT_OTHER -> {
                mContentOther.isChecked = true
                mContentOtherLayout.visibility = View.VISIBLE
                mContentOtherEdit.setText(mOrderInfo.content_other)
            }
        }


        mColorEdit.setText(orderInfo.color)
        mSizeEdit.setText(orderInfo.size)
        mTransformEdit.setText(orderInfo.transform)
        mPromiseDateEdit.setText(orderInfo.promiseDate)
        mOtherEdit.setText(orderInfo.other)
    }

    private fun setOrderInfoText(orderInfo: OrderInfo) {
        mNameText.setText(orderInfo.name)
        mEmailText.setText(orderInfo.email)
        mTelText.setText(orderInfo.tel)

        mProductNameText.setText(orderInfo.productName)
        mOrderDateText.setText(orderInfo.orderDate)
        mReleaseDateText.setText(orderInfo.releaseSchedule)
        mCoastPriceText.setText(orderInfo.coastPrice)
        mSellingPriceText.setText(orderInfo.sellingPrice)
        mReleaseText.setText(orderInfo.releaseYN)
//        mProduceImageText.setText(orderInfo.productImage)
        mShippingAddressText.setText(orderInfo.shippingAddress)
        mAccountNameText.setText(orderInfo.accountName)
//        mContentsText.setText(orderInfo.content)

        mContentOtherText.setText(orderInfo.content_other)

        when(orderInfo.content) {
            Const.ORDER_CONTENT_14K -> {
                mContent14K.isChecked = true
            }
            Const.ORDER_CONTENT_18K -> {
                mContent18K.isChecked = true
            }
            Const.ORDER_CONTENT_24K -> {
                mContent24K.isChecked = true
            }
            Const.ORDER_CONTENT_OTHER -> {
                mContentOther.isChecked = true
            }
        }

        mColorText.setText(orderInfo.color)
        mSizeText.setText(orderInfo.size)
        mTransformText.setText(orderInfo.transform)
        mPromiseDateText.setText(orderInfo.promiseDate)
        mOtherText.setText(orderInfo.other)
        orderInfo?.productImage?.let {
            Log.e("151515", "setOrderinfoText, product Image uri= " + it)
            setImageUri(Uri.parse(it))
        }.let {
            Log.e("151515", "setOrderinfoText, product Image uri null")
        }
    }

    private fun setMode(isAdd : Boolean) {
        if(isAdd) {
            if(!this.isAdd) {
                this.isChange = true
                mDialogView.findViewById<TextView>(R.id.dialog_add_order_title).setText(R.string.change_order)
            }

            mReleaseY.isClickable = true
            mReleaseN.isClickable = true

            mContent14K.isClickable = true
            mContent18K.isClickable = true
            mContent24K.isClickable = true
            mContentOther.isClickable = true

            mDialogView.findViewById<LinearLayout>(R.id.dailog_add_order_add_btn_layout).visibility = View.VISIBLE
            mDialogView.findViewById<LinearLayout>(R.id.dailog_add_order_detail_btn_layout).visibility = View.GONE

            mNameEdit.visibility = View.VISIBLE
            mEmailEdit.visibility = View.VISIBLE
            mTelEdit.visibility = View.VISIBLE
            mProductNameEdit.visibility = View.VISIBLE
            mOrderDateEdit.visibility = View.VISIBLE
            mReleaseDateEdit.visibility = View.VISIBLE
            mCoastPriceEdit.visibility = View.VISIBLE
            mSellingPriceEdit.visibility = View.VISIBLE
            mReleaseLayout.visibility = View.VISIBLE
//            mProduceImageEdit.visibility = View.VISIBLE
            mShippingAddressEdit.visibility = View.VISIBLE
            mAccountNameEdit.visibility = View.VISIBLE
//            mContentsEdit.visibility = View.VISIBLE
            mContentOtherEdit.visibility = View.VISIBLE
            mColorEdit.visibility = View.VISIBLE
            mSizeEdit.visibility = View.VISIBLE
            mTransformEdit.visibility = View.VISIBLE
            mPromiseDateEdit.visibility = View.VISIBLE
            mOtherEdit.visibility = View.VISIBLE

            mNameText.visibility = View.GONE
            mEmailText.visibility = View.GONE
            mTelText.visibility = View.GONE
            mProductNameText.visibility = View.GONE
            mOrderDateText.visibility = View.GONE
            mReleaseDateText.visibility = View.GONE
            mCoastPriceText.visibility = View.GONE
            mSellingPriceText.visibility = View.GONE
            mReleaseText.visibility = View.GONE
//            mProduceImageText.visibility = View.GONE
            mShippingAddressText.visibility = View.GONE
            mAccountNameText.visibility = View.GONE
//            mContentsText.visibility = View.GONE
            mContentOtherText.visibility = View.GONE
            mColorText.visibility = View.GONE
            mSizeText.visibility = View.GONE
            mTransformText.visibility = View.GONE
            mPromiseDateText.visibility = View.GONE
            mOtherText.visibility = View.GONE
        } else {
            if(!this.isAdd) {
                this.isChange = false
                mDialogView.findViewById<TextView>(R.id.dialog_add_order_title).setText(R.string.confirm_order)
            }

            mReleaseY.isClickable = false
            mReleaseN.isClickable = false

            mContent14K.isClickable = false
            mContent18K.isClickable = false
            mContent24K.isClickable = false
            mContentOther.isClickable = false


            mDialogView.findViewById<LinearLayout>(R.id.dailog_add_order_add_btn_layout).visibility = View.GONE
            mDialogView.findViewById<LinearLayout>(R.id.dailog_add_order_detail_btn_layout).visibility = View.VISIBLE

            mNameEdit.visibility = View.GONE
            mEmailEdit.visibility = View.GONE
            mTelEdit.visibility = View.GONE
            mProductNameEdit.visibility = View.GONE
            mOrderDateEdit.visibility = View.GONE
            mReleaseDateEdit.visibility = View.GONE
            mCoastPriceEdit.visibility = View.GONE
            mSellingPriceEdit.visibility = View.GONE
            mReleaseLayout.visibility = View.GONE
//            mProduceImageEdit.visibility = View.GONE
            mShippingAddressEdit.visibility = View.GONE
            mAccountNameEdit.visibility = View.GONE
//            mContentsEdit.visibility = View.GONE
            mContentOtherEdit.visibility = View.GONE
            mColorEdit.visibility = View.GONE
            mSizeEdit.visibility = View.GONE
            mTransformEdit.visibility = View.GONE
            mPromiseDateEdit.visibility = View.GONE
            mOtherEdit.visibility = View.GONE

            mNameText.visibility = View.VISIBLE
            mEmailText.visibility = View.VISIBLE
            mTelText.visibility = View.VISIBLE
            mProductNameText.visibility = View.VISIBLE
            mOrderDateText.visibility = View.VISIBLE
            mReleaseDateText.visibility = View.VISIBLE
            mCoastPriceText.visibility = View.VISIBLE
            mSellingPriceText.visibility = View.VISIBLE
            mReleaseText.visibility = View.VISIBLE
//            mProduceImageText.visibility = View.VISIBLE
            mShippingAddressText.visibility = View.VISIBLE
            mAccountNameText.visibility = View.VISIBLE
//            mContentsText.visibility = View.VISIBLE
            mContentOtherText.visibility = View.VISIBLE
            mColorText.visibility = View.VISIBLE
            mSizeText.visibility = View.VISIBLE
            mTransformText.visibility = View.VISIBLE
            mPromiseDateText.visibility = View.VISIBLE
            mOtherText.visibility = View.VISIBLE
        }
    }

    private fun saveOrderInfo(): Long {
        var orderInfo = OrderInfo()
        var result: Long

        if(!isAdd && mOrderInfo != null) {
            orderInfo.index = mOrderInfo.index
        }

        orderInfo.name = mNameEdit.text.toString()
        orderInfo.email = mEmailEdit.text.toString()
        orderInfo.tel = mTelEdit.text.toString()

        orderInfo.productName = mProductNameEdit.text.toString()
        if(TextUtils.isEmpty(mOrderDateEdit.text.toString())) {
            val timeZone = TimeZone.getTimeZone("Asia/Seoul")
            val calendar = GregorianCalendar(timeZone)

            orderInfo.orderDate = "${calendar.get(GregorianCalendar.YEAR)}.${calendar.get(GregorianCalendar.MONTH)+1}.${calendar.get(GregorianCalendar.DATE)}"
        } else {
            orderInfo.orderDate = mOrderDateEdit.text.toString()
        }
        orderInfo.releaseSchedule = mReleaseDateEdit.text.toString()
        orderInfo.coastPrice = mCoastPriceEdit.text.toString()
        orderInfo.sellingPrice = mSellingPriceEdit.text.toString()
        orderInfo.releaseYN = mReleaseEdit.text.toString()
        if(mReleaseY.isChecked) {
            orderInfo.releaseYN = "Y"
        } else {
            orderInfo.releaseYN = "N"
        }
//        orderInfo.productImage = mProduceImageEdit.text.toString()
        orderInfo.shippingAddress = mShippingAddressEdit.text.toString()
        orderInfo.accountName = mAccountNameEdit.text.toString()

        if(mContent14K.isChecked) {
            orderInfo.content = 0
        } else if(mContent18K.isChecked) {
            orderInfo.content = 1
        } else if(mContent24K.isChecked) {
            orderInfo.content = 2
        } else {
            orderInfo.content = 3
        }

        orderInfo.content_other = mContentOtherEdit.text.toString()

        orderInfo.color = mColorEdit.text.toString()
        orderInfo.size = mSizeEdit.text.toString()
        orderInfo.transform = mTransformEdit.text.toString()
        orderInfo.promiseDate = mPromiseDateEdit.text.toString()
        orderInfo.other = mOtherEdit.text.toString()
        orderInfo.productImage = mProductImageUri.toString()

        result = mDb?.setOrder(orderInfo)!!

        if(result < 0) {
            // 저장오류
            Toast.makeText(mContext, "db 저장오류", Toast.LENGTH_SHORT).show()
        } else {
            mOrderInfo = orderInfo
        }

        return result
    }

    private fun showDatePicker() {
        var date = ""

        var year = -1
        var month = -1
        var day = -1
        if(datePickerMode == DATE_PICKER_ORDER) {
            date = mOrderDateEdit.text.toString()
        } else if(datePickerMode == DATE_PICKER_RELEASE) {
            date = mReleaseDateEdit.text.toString()
        } else {
            date = mPromiseDateEdit.text.toString()
        }

        if(TextUtils.isEmpty(date)) {
            val timeZone = TimeZone.getTimeZone("Asia/Seoul")
            val calendar = GregorianCalendar(timeZone)

            year = calendar.get(GregorianCalendar.YEAR)
            month = calendar.get(GregorianCalendar.MONTH)
            day = calendar.get(GregorianCalendar.DATE)
        } else {
            var da: Date? = null
            try {
                da = SimpleDateFormat("yyyy.MM.dd").parse(date)
                var calendar = Calendar.getInstance()
                calendar.time = da
                year = calendar.get(GregorianCalendar.YEAR)
                month = calendar.get(GregorianCalendar.MONTH)
                day = calendar.get(GregorianCalendar.DATE)

            } catch (e: ParseException) {
                e.printStackTrace()
                val timeZone = TimeZone.getTimeZone("Asia/Seoul")
                val calendar = GregorianCalendar(timeZone)

                year = calendar.get(GregorianCalendar.YEAR)
                month = calendar.get(GregorianCalendar.MONTH)
                day = calendar.get(GregorianCalendar.DATE)
            }
        }

        if(datePickerDialog != null && datePickerDialog!!.isShowing) {
            datePickerDialog!!.dismiss()
        }

        datePickerDialog = DatePickerDialog(mContext, DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->

            var selectDate = "${y}.${m+1}.${d}"

            if(datePickerMode == DATE_PICKER_ORDER) {
                mOrderDateEdit.setText(selectDate)
            } else if(datePickerMode == DATE_PICKER_RELEASE) {
                mReleaseDateEdit.setText(selectDate)
            } else {
                mPromiseDateEdit.setText(selectDate)
            }
        }, year, month, day)

        datePickerDialog!!.show()
    }

    fun setProductImage() {
        // order
    }

    fun selectGallerty() {
        var intent = Intent()

        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        (mContext as OrderListActivity).startActivityForResult(intent, Const.REQUEST_CODE_IMAGE)
    }

    fun setImageUri(uri: Uri?) {
        mProductImageUri = uri;
        try {
            mProductImage.setImageURI(uri)
        } catch (e: Exception) {
            Log.e("151515", "image load error:" + e.toString())
            mProductImage.setImageResource(R.drawable.default_img)
        }
    }

    fun selectCustomer(customerInfo: CustomerInfo) {
        mNameEdit.setText(customerInfo.name)
        mTelEdit.setText(customerInfo.tel)
        mEmailEdit.setText(customerInfo.email)
    }
}