package com.oss.form;

import java.time.Month;

public class ReadingForm {
        private int readingValue;
        private Long smartDeviceId;
        private int readingMonth;
        private int readingYear;

        public int getReadingValue() {
                return readingValue;
        }

        public void setReadingValue(int readingValue) {
                this.readingValue = readingValue;
        }

        public Long getSmartDeviceId() {
                return smartDeviceId;
        }

        public void setSmartDeviceId(Long smartDeviceId) {
                this.smartDeviceId = smartDeviceId;
        }

        public int getReadingMonth() {
                return readingMonth;
        }

        public void setReadingMonth(int readingMonth) {
                this.readingMonth = readingMonth;
        }

        public int getReadingYear() {
                return readingYear;
        }

        public void setReadingYear(int readingYear) {
                this.readingYear = readingYear;
        }
}

