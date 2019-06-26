package com.ian.ordernote.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.ian.ordernote.CustomerListActivity
import com.ian.ordernote.R
import com.ian.ordernote.core.CommonAlertDialog
import com.ian.ordernote.data.OrderInfo
import com.ian.ordernote.db.DB
import org.w3c.dom.Text

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
                if(isAdd) {
                    if (isShowing) {
                        dismiss()
                    }
                } else {
                    // 수정중인 order info cancel popup
                    showAlertDialog(mContext, "주문내역 수정을 취소하시겠습니까? 수정중이던 내용은 저장되지 않습니다.",
                        mContext.getString(R.string.confirm),
                        mContext.getString(R.string.cancel),
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            setMode(false)
                        },
                        null)
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
            setOrderInfoText(mOrderInfo)
            mDialogView.findViewById<TextView>(R.id.dialog_add_order_title).setText(R.string.confirm_order)
        }
        setMode(isAdd)
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

    fun setOrderInfo(orderInfo: OrderInfo) {
        mOrderInfo = orderInfo
    }

    private fun setOrderInfoEdit(orderInfo: OrderInfo) {
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

    private fun setOrderInfoText(orderInfo: OrderInfo) {
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

    private fun setMode(isAdd : Boolean) {
        if(isAdd) {
            if(!this.isAdd) {
                mDialogView.findViewById<TextView>(R.id.dialog_add_order_title).setText(R.string.change_order)
            }
            mDialogView.findViewById<LinearLayout>(R.id.dailog_add_order_add_btn_layout).visibility = View.VISIBLE
            mDialogView.findViewById<LinearLayout>(R.id.dailog_add_order_detail_btn_layout).visibility = View.GONE

            mNameEdit.visibility = View.VISIBLE
            mMobileEdit.visibility = View.VISIBLE
            mEmailEdit.visibility = View.VISIBLE
            mTelEdit.visibility = View.VISIBLE
            mProductNameEdit.visibility = View.VISIBLE
            mOrderDateEdit.visibility = View.VISIBLE
            mReleaseDateEdit.visibility = View.VISIBLE
            mCoastPriceEdit.visibility = View.VISIBLE
            mSellingPriceEdit.visibility = View.VISIBLE
            mReleaseEdit.visibility = View.VISIBLE
            mProduceImageEdit.visibility = View.VISIBLE
            mShippingAddressEdit.visibility = View.VISIBLE
            mAccountNameEdit.visibility = View.VISIBLE
            mContentsEdit.visibility = View.VISIBLE
            mColorEdit.visibility = View.VISIBLE
            mSizeEdit.visibility = View.VISIBLE
            mTransformEdit.visibility = View.VISIBLE
            mPromiseDateEdit.visibility = View.VISIBLE
            mOtherEdit.visibility = View.VISIBLE

            mNameText.visibility = View.GONE
            mMobileText.visibility = View.GONE
            mEmailText.visibility = View.GONE
            mTelText.visibility = View.GONE
            mProductNameText.visibility = View.GONE
            mOrderDateText.visibility = View.GONE
            mReleaseDateText.visibility = View.GONE
            mCoastPriceText.visibility = View.GONE
            mSellingPriceText.visibility = View.GONE
            mReleaseText.visibility = View.GONE
            mProduceImageText.visibility = View.GONE
            mShippingAddressText.visibility = View.GONE
            mAccountNameText.visibility = View.GONE
            mContentsText.visibility = View.GONE
            mColorText.visibility = View.GONE
            mSizeText.visibility = View.GONE
            mTransformText.visibility = View.GONE
            mPromiseDateText.visibility = View.GONE
            mOtherText.visibility = View.GONE
        } else {
            if(!this.isAdd) {
                mDialogView.findViewById<TextView>(R.id.dialog_add_order_title).setText(R.string.confirm_order)
            }
            mDialogView.findViewById<LinearLayout>(R.id.dailog_add_order_add_btn_layout).visibility = View.GONE
            mDialogView.findViewById<LinearLayout>(R.id.dailog_add_order_detail_btn_layout).visibility = View.VISIBLE

            mNameEdit.visibility = View.GONE
            mMobileEdit.visibility = View.GONE
            mEmailEdit.visibility = View.GONE
            mTelEdit.visibility = View.GONE
            mProductNameEdit.visibility = View.GONE
            mOrderDateEdit.visibility = View.GONE
            mReleaseDateEdit.visibility = View.GONE
            mCoastPriceEdit.visibility = View.GONE
            mSellingPriceEdit.visibility = View.GONE
            mReleaseEdit.visibility = View.GONE
            mProduceImageEdit.visibility = View.GONE
            mShippingAddressEdit.visibility = View.GONE
            mAccountNameEdit.visibility = View.GONE
            mContentsEdit.visibility = View.GONE
            mColorEdit.visibility = View.GONE
            mSizeEdit.visibility = View.GONE
            mTransformEdit.visibility = View.GONE
            mPromiseDateEdit.visibility = View.GONE
            mOtherEdit.visibility = View.GONE

            mNameText.visibility = View.VISIBLE
            mMobileText.visibility = View.VISIBLE
            mEmailText.visibility = View.VISIBLE
            mTelText.visibility = View.VISIBLE
            mProductNameText.visibility = View.VISIBLE
            mOrderDateText.visibility = View.VISIBLE
            mReleaseDateText.visibility = View.VISIBLE
            mCoastPriceText.visibility = View.VISIBLE
            mSellingPriceText.visibility = View.VISIBLE
            mReleaseText.visibility = View.VISIBLE
            mProduceImageText.visibility = View.VISIBLE
            mShippingAddressText.visibility = View.VISIBLE
            mAccountNameText.visibility = View.VISIBLE
            mContentsText.visibility = View.VISIBLE
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
        } else {
            mOrderInfo = orderInfo
        }

        return result
    }
}