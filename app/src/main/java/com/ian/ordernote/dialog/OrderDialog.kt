package com.ian.ordernote.dialog

import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ian.ordernote.CustomerListActivity
import com.ian.ordernote.R
import com.ian.ordernote.core.CommonAlertDialog
import com.ian.ordernote.data.OrderInfo
import com.ian.ordernote.db.DB

class OrderDialog(context: Context?, listener: CustomerListActivity.CustomerChangeListener) : CommonAlertDialog(context), View.OnClickListener {

    var isAdd = true
    lateinit var mOrderInfo: OrderInfo
    lateinit var mTempOrderInfo: OrderInfo

    lateinit var mContext: Context
    lateinit var mBuilder: AlertDialog.Builder
    lateinit var mDialogView: View
    lateinit var mListener: CustomerListActivity.CustomerChangeListener

    var mDb : DB? = null

    lateinit var mNameEdit: EditText
    lateinit var mMobileEdit: EditText
    lateinit var mTelEdit: EditText
    lateinit var mEmailEdit: EditText
    lateinit var mProductNameEdit: EditText
    lateinit var mOrderDateEdit: EditText
    lateinit var mReleaseDateEdit: EditText
    lateinit var mCoastPriceEdit: EditText
    lateinit var mSellingPriceEdit: EditText
    lateinit var mReleaseEdit: EditText
    lateinit var mProduceImageEdit: EditText
    lateinit var mShippingAddressEdit: EditText
    lateinit var mAccountNameEdit: EditText
    lateinit var mContentsEdit: EditText
    lateinit var mColorEdit: EditText
    lateinit var mSizeEdit: EditText
    lateinit var mTransformEdit: EditText
    lateinit var mPromiseDateEdit: EditText
    lateinit var mOtherEdit: EditText

    lateinit var mNameText: TextView
    lateinit var mMobileText: TextView
    lateinit var mTelText: TextView
    lateinit var mEmailText: TextView
    lateinit var mProductNameText: TextView
    lateinit var mOrderDateText: TextView
    lateinit var mReleaseDateText: TextView
    lateinit var mCoastPriceText: TextView
    lateinit var mSellingPriceText: TextView
    lateinit var mReleaseText: TextView
    lateinit var mProduceImageText: TextView
    lateinit var mShippingAddressText: TextView
    lateinit var mAccountNameText: TextView
    lateinit var mContentsText: TextView
    lateinit var mColorText: TextView
    lateinit var mSizeText: TextView
    lateinit var mTransformText: TextView
    lateinit var mPromiseDateText: TextView
    lateinit var mOtherText: TextView

    init {
        mDb = DB(context).getInstance(context!!)
        mListener = listener

        mContext = context!!
        mDialogView = layoutInflater.inflate(R.layout.dialog_add_order, null)
        setView(mDialogView)

        mNameEdit = mDialogView.findViewById(R.id.dialog_add_order_name)
        mMobileEdit = mDialogView.findViewById(R.id.dialog_add_order_mobile)
        mTelEdit = mDialogView.findViewById(R.id.dialog_add_order_tel)
        mEmailEdit = mDialogView.findViewById(R.id.dialog_add_order_email)
        mProductNameEdit = mDialogView.findViewById(R.id.dialog_add_order_product_name)
        mOrderDateEdit = mDialogView.findViewById(R.id.dialog_add_order_date)
        mReleaseDateEdit = mDialogView.findViewById(R.id.dialog_add_order_release_schedule)
        mCoastPriceEdit = mDialogView.findViewById(R.id.dialog_add_order_coast_price)
        mSellingPriceEdit = mDialogView.findViewById(R.id.dialog_add_order_selling_price)
        mReleaseEdit = mDialogView.findViewById(R.id.dialog_add_order_release)
        mProduceImageEdit = mDialogView.findViewById(R.id.dialog_add_order_product_image)
        mShippingAddressEdit = mDialogView.findViewById(R.id.dialog_add_order_shipping_address)
        mAccountNameEdit = mDialogView.findViewById(R.id.dialog_add_order_account_name)
        mContentsEdit = mDialogView.findViewById(R.id.dialog_add_order_content)
        mColorEdit = mDialogView.findViewById(R.id.dialog_add_order_color)
        mSizeEdit = mDialogView.findViewById(R.id.dialog_add_order_size)
        mTransformEdit = mDialogView.findViewById(R.id.dialog_add_order_transform)
        mPromiseDateEdit = mDialogView.findViewById(R.id.dialog_add_order_promise_date)
        mOtherEdit = mDialogView.findViewById(R.id.dialog_add_order_other)

        mNameText = mDialogView.findViewById(R.id.dialog_add_order_name_text)
        mMobileText= mDialogView.findViewById(R.id.dialog_add_order_mobile_text)
        mTelText = mDialogView.findViewById(R.id.dialog_add_order_tel_text)
        mEmailText = mDialogView.findViewById(R.id.dialog_add_order_email_text)
        mProductNameText = mDialogView.findViewById(R.id.dialog_add_order_product_name_text)
        mOrderDateText = mDialogView.findViewById(R.id.dialog_add_order_date_text)
        mReleaseDateText = mDialogView.findViewById(R.id.dialog_add_order_release_schedule_text)
        mCoastPriceText = mDialogView.findViewById(R.id.dialog_add_order_coast_price_text)
        mSellingPriceText = mDialogView.findViewById(R.id.dialog_add_order_selling_price_text)
        mReleaseText = mDialogView.findViewById(R.id.dialog_add_order_release_text)
        mProduceImageText = mDialogView.findViewById(R.id.dialog_add_order_product_image_text)
        mShippingAddressText = mDialogView.findViewById(R.id.dialog_add_order_shipping_address_text)
        mAccountNameText = mDialogView.findViewById(R.id.dialog_add_order_account_name_text)
        mContentsText = mDialogView.findViewById(R.id.dialog_add_order_content_text)
        mColorText = mDialogView.findViewById(R.id.dialog_add_order_color_text)
        mSizeText = mDialogView.findViewById(R.id.dialog_add_order_size_text)
        mTransformText = mDialogView.findViewById(R.id.dialog_add_order_transform_text)
        mPromiseDateText = mDialogView.findViewById(R.id.dialog_add_order_promise_date_text)
        mOtherText = mDialogView.findViewById(R.id.dialog_add_order_other_text)

        mDialogView.findViewById<Button>(R.id.dialog_add_order_cancel_btn).setOnClickListener(this)
        mDialogView.findViewById<Button>(R.id.dialog_add_order_save_btn).setOnClickListener(this)
        mDialogView.findViewById<Button>(R.id.dialog_add_order_confirm_btn).setOnClickListener(this)
        mDialogView.findViewById<Button>(R.id.dialog_add_order_change_btn).setOnClickListener(this)
    }

    override fun dismiss() {
        super.dismiss()
        Log.e("151515", "OrderDialog dismiss")
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.dialog_add_order_cancel_btn -> { // Add cancel
                if(isShowing) {
                    dismiss()
                }
            }

            R.id.dialog_add_order_save_btn -> { // Add 저장

                if(TextUtils.isEmpty(mNameEdit.text)
                    || TextUtils.isEmpty(mMobileEdit.text)
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
                        if (isShowing) {
                            dismiss()
                        }
                    }
                }
            }

            R.id.dialog_add_order_change_btn -> { // 수정 버튼

            }

            R.id.dialog_add_order_confirm_btn -> { // 확인 버튼

            }
        }
    }

    override fun show() {
        super.show()

        if(isAdd) {
            init()
            mOrderInfo = OrderInfo()
            mTempOrderInfo = OrderInfo()
        } else {
            mTempOrderInfo = mOrderInfo
        }
    }

    fun init() {
        mNameEdit.setText("")
        mMobileEdit.setText("")
        mEmailEdit.setText("")
        mTelEdit.setText("")

        mProductNameEdit.setText("")
        mOrderDateEdit.setText("")
        mReleaseDateEdit.setText("")
        mCoastPriceEdit.setText("")
        mSellingPriceEdit.setText("")
        mReleaseEdit.setText("")
        mProduceImageEdit.setText("")
        mShippingAddressEdit.setText("")
        mAccountNameEdit.setText("")
        mContentsEdit.setText("")
        mColorEdit.setText("")
        mSizeEdit.setText("")
        mTransformEdit.setText("")
        mPromiseDateEdit.setText("")
        mOtherEdit.setText("")
    }

    fun setOrderInfoEdit(orderInfo: OrderInfo) {
        mNameEdit.setText(orderInfo.name)
        mMobileEdit.setText(orderInfo.mobile)
        mEmailEdit.setText(orderInfo.email)
        mTelEdit.setText(orderInfo.tel)

        mProductNameEdit.setText(orderInfo.productName)
        mOrderDateEdit.setText(orderInfo.orderDate)
        mReleaseDateEdit.setText(orderInfo.releaseSchedule)
        mCoastPriceEdit.setText(orderInfo.coastPrice)
        mSellingPriceEdit.setText(orderInfo.sellingPrice)
        mReleaseEdit.setText(orderInfo.releaseYN)
        mProduceImageEdit.setText(orderInfo.productImage)
        mShippingAddressEdit.setText(orderInfo.shippingAddress)
        mAccountNameEdit.setText(orderInfo.accountName)
        mContentsEdit.setText(orderInfo.content)
        mColorEdit.setText(orderInfo.color)
        mSizeEdit.setText(orderInfo.size)
        mTransformEdit.setText(orderInfo.transform)
        mPromiseDateEdit.setText(orderInfo.promiseDate)
        mOtherEdit.setText(orderInfo.other)
    }

    fun setOrderInfoText(orderInfo: OrderInfo) {
        mNameText.setText(orderInfo.name)
        mMobileText.setText(orderInfo.mobile)
        mEmailText.setText(orderInfo.email)
        mTelText.setText(orderInfo.tel)

        mProductNameText.setText(orderInfo.productName)
        mOrderDateText.setText(orderInfo.orderDate)
        mReleaseDateText.setText(orderInfo.releaseSchedule)
        mCoastPriceText.setText(orderInfo.coastPrice)
        mSellingPriceText.setText(orderInfo.sellingPrice)
        mReleaseText.setText(orderInfo.releaseYN)
        mProduceImageText.setText(orderInfo.productImage)
        mShippingAddressText.setText(orderInfo.shippingAddress)
        mAccountNameText.setText(orderInfo.accountName)
        mContentsText.setText(orderInfo.content)
        mColorText.setText(orderInfo.color)
        mSizeText.setText(orderInfo.size)
        mTransformText.setText(orderInfo.transform)
        mPromiseDateText.setText(orderInfo.promiseDate)
        mOtherText.setText(orderInfo.other)
    }

    fun saveOrderInfo(): Long {
        var orderInfo = OrderInfo()
        var result: Long

        orderInfo.name = mNameEdit.text.toString()
        orderInfo.mobile = mMobileEdit.text.toString()
        orderInfo.email = mEmailEdit.text.toString()
        orderInfo.tel = mTelEdit.text.toString()

        orderInfo.productName = mProductNameEdit.text.toString()
        orderInfo.orderDate = mOrderDateEdit.text.toString()
        orderInfo.releaseSchedule = mReleaseDateEdit.text.toString()
        orderInfo.coastPrice = mCoastPriceEdit.text.toString()
        orderInfo.sellingPrice = mSellingPriceEdit.text.toString()
        orderInfo.releaseYN = mReleaseEdit.text.toString()
        orderInfo.productImage = mProduceImageEdit.text.toString()
        orderInfo.shippingAddress = mShippingAddressEdit.text.toString()
        orderInfo.accountName = mAccountNameEdit.text.toString()
        orderInfo.content = mContentsEdit.text.toString()
        orderInfo.color = mColorEdit.text.toString()
        orderInfo.size = mSizeEdit.text.toString()
        orderInfo.transform = mTransformEdit.text.toString()
        orderInfo.promiseDate = mPromiseDateEdit.text.toString()
        orderInfo.other = mOtherEdit.text.toString()


        result = mDb?.setOrder(orderInfo)!!

        if(result < 0) {
            // 저장오류
            Toast.makeText(mContext, "db 저장오류", Toast.LENGTH_SHORT).show()
        }

        return result
    }
}