package com.app.juawcevada.revoluttest.ui

import com.app.juawcevada.revoluttest.model.ExchangeEntity
import com.app.juawcevada.revoluttest.shared.Mapper
import java.util.*
import javax.inject.Inject

class ExchangeEntityToExchangeListMapper @Inject constructor() : Mapper<ExchangeEntity, List<ExchangeItem>>() {

    override fun mapFrom(from: ExchangeEntity) = ArrayList<ExchangeItem>().apply {
        add(ExchangeItem(from.base, getCurrencyName(from.base), getCurrencyImage(from.base), 1.0f))
        with(from.rates) {
            AUD?.let {
                add(ExchangeItem(AUD_CODE, getCurrencyName(AUD_CODE), getCurrencyImage(AUD_CODE), it))
            }
            BGN?.let {
                add(ExchangeItem(BGN_CODE, getCurrencyName(BGN_CODE), getCurrencyImage(BGN_CODE), it))
            }
            BRL?.let {
                add(ExchangeItem(BRL_CODE, getCurrencyName(BRL_CODE), getCurrencyImage(BRL_CODE), it))
            }
            CAD?.let {
                add(ExchangeItem(CAD_CODE, getCurrencyName(CAD_CODE), getCurrencyImage(CAD_CODE), it))
            }
            CHF?.let {
                add(ExchangeItem(CHF_CODE, getCurrencyName(CHF_CODE), getCurrencyImage(CHF_CODE), it))
            }
            CNY?.let {
                add(ExchangeItem(CNY_CODE, getCurrencyName(CNY_CODE), getCurrencyImage(CNY_CODE), it))
            }
            CZK?.let {
                add(ExchangeItem(CZK_CODE, getCurrencyName(CZK_CODE), getCurrencyImage(CZK_CODE), it))
            }
            DKK?.let {
                add(ExchangeItem(DKK_CODE, getCurrencyName(DKK_CODE), getCurrencyImage(DKK_CODE), it))
            }
            GBP?.let {
                add(ExchangeItem(GBP_CODE, getCurrencyName(GBP_CODE), getCurrencyImage(GBP_CODE), it))
            }
            HKD?.let {
                add(ExchangeItem(HKD_CODE, getCurrencyName(HKD_CODE), getCurrencyImage(HKD_CODE), it))
            }
            HRK?.let {
                add(ExchangeItem(HRK_CODE, getCurrencyName(HRK_CODE), getCurrencyImage(HRK_CODE), it))
            }
            HUF?.let {
                add(ExchangeItem(HUF_CODE, getCurrencyName(HUF_CODE), getCurrencyImage(HUF_CODE), it))
            }
            IDR?.let {
                add(ExchangeItem(IDR_CODE, getCurrencyName(IDR_CODE), getCurrencyImage(IDR_CODE), it))
            }
            ILS?.let {
                add(ExchangeItem(ILS_CODE, getCurrencyName(ILS_CODE), getCurrencyImage(ILS_CODE), it))
            }
            INR?.let {
                add(ExchangeItem(INR_CODE, getCurrencyName(INR_CODE), getCurrencyImage(INR_CODE), it))
            }
            ISK?.let {
                add(ExchangeItem(ISK_CODE, getCurrencyName(ISK_CODE), getCurrencyImage(ISK_CODE), it))
            }
            JPY?.let {
                add(ExchangeItem(JPY_CODE, getCurrencyName(JPY_CODE), getCurrencyImage(JPY_CODE), it))
            }
            KRW?.let {
                add(ExchangeItem(KRW_CODE, getCurrencyName(KRW_CODE), getCurrencyImage(KRW_CODE), it))
            }
            MXN?.let {
                add(ExchangeItem(MXN_CODE, getCurrencyName(MXN_CODE), getCurrencyImage(MXN_CODE), it))
            }
            MYR?.let {
                add(ExchangeItem(MYR_CODE, getCurrencyName(MYR_CODE), getCurrencyImage(MYR_CODE), it))
            }
            NOK?.let {
                add(ExchangeItem(NOK_CODE, getCurrencyName(NOK_CODE), getCurrencyImage(NOK_CODE), it))
            }
            NZD?.let {
                add(ExchangeItem(NZD_CODE, getCurrencyName(NZD_CODE), getCurrencyImage(NZD_CODE), it))
            }
            PHP?.let {
                add(ExchangeItem(PHP_CODE, getCurrencyName(PHP_CODE), getCurrencyImage(PHP_CODE), it))
            }
            PLN?.let {
                add(ExchangeItem(PLN_CODE, getCurrencyName(PLN_CODE), getCurrencyImage(PLN_CODE), it))
            }
            RON?.let {
                add(ExchangeItem(RON_CODE, getCurrencyName(RON_CODE), getCurrencyImage(RON_CODE), it))
            }
            RUB?.let {
                add(ExchangeItem(RUB_CODE, getCurrencyName(RUB_CODE), getCurrencyImage(RUB_CODE), it))
            }
            SEK?.let {
                add(ExchangeItem(SEK_CODE, getCurrencyName(SEK_CODE), getCurrencyImage(SEK_CODE), it))
            }
            SGD?.let {
                add(ExchangeItem(SGD_CODE, getCurrencyName(SGD_CODE), getCurrencyImage(SGD_CODE), it))
            }
            THB?.let {
                add(ExchangeItem(THB_CODE, getCurrencyName(THB_CODE), getCurrencyImage(THB_CODE), it))
            }
            TRY?.let {
                add(ExchangeItem(TRY_CODE, getCurrencyName(TRY_CODE), getCurrencyImage(TRY_CODE), it))
            }
            USD?.let {
                add(ExchangeItem(USD_CODE, getCurrencyName(USD_CODE), getCurrencyImage(USD_CODE), it))
            }
            ZAR?.let {
                add(ExchangeItem(ZAR_CODE, getCurrencyName(ZAR_CODE), getCurrencyImage(ZAR_CODE), it))
            }
            EUR?.let {
                add(ExchangeItem(EUR_CODE, getCurrencyName(EUR_CODE), getCurrencyImage(EUR_CODE), it))
            }
        }

    }

    companion object {

        private const val AUD_CODE = "AUD"
        private const val BGN_CODE = "BGN"
        private const val BRL_CODE = "BRL"
        private const val CAD_CODE = "CAD"
        private const val CHF_CODE = "CHF"
        private const val CNY_CODE = "CNY"
        private const val CZK_CODE = "CZK"
        private const val DKK_CODE = "DKK"
        private const val GBP_CODE = "GBP"
        private const val HKD_CODE = "HKD"
        private const val HRK_CODE = "HRK"
        private const val HUF_CODE = "HUF"
        private const val IDR_CODE = "IDR"
        private const val ILS_CODE = "ILS"
        private const val INR_CODE = "INR"
        private const val ISK_CODE = "ISK"
        private const val JPY_CODE = "JPY"
        private const val KRW_CODE = "KRW"
        private const val MXN_CODE = "MXN"
        private const val MYR_CODE = "MYR"
        private const val NOK_CODE = "NOK"
        private const val NZD_CODE = "NZD"
        private const val PHP_CODE = "PHP"
        private const val PLN_CODE = "PLN"
        private const val RON_CODE = "RON"
        private const val RUB_CODE = "RUB"
        private const val SEK_CODE = "SEK"
        private const val SGD_CODE = "SGD"
        private const val THB_CODE = "THB"
        private const val TRY_CODE = "TRY"
        private const val USD_CODE = "USD"
        private const val ZAR_CODE = "ZAR"
        private const val EUR_CODE = "EUR"

        fun getCurrencyName(currencyCode: String) = Currency.getInstance(currencyCode).displayName

        fun getCurrencyImage(currencyCode: String): String {
            return when (currencyCode) {
                AUD_CODE -> "http://flags.fmcdn.net/data/flags/w1160/au.png"
                BGN_CODE -> "http://flags.fmcdn.net/data/flags/w1160/bg.png"
                BRL_CODE -> "http://flags.fmcdn.net/data/flags/w1160/br.png"
                CAD_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                CHF_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                CNY_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                CZK_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                DKK_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                GBP_CODE -> "http://flags.fmcdn.net/data/flags/w1160/gb.png"
                HKD_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                HRK_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                HUF_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                IDR_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                ILS_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                INR_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                ISK_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                JPY_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                KRW_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                MXN_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                MYR_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                NOK_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                NZD_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                PHP_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                PLN_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                RON_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                RUB_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                SEK_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                SGD_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                THB_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                TRY_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                USD_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                ZAR_CODE -> "http://flags.fmcdn.net/data/flags/w1160/ca.png"
                EUR_CODE -> "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/Flag_of_Europe.svg/510px-Flag_of_Europe.svg.png"
                else -> ""
            }
        }
    }
}