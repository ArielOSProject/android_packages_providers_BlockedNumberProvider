/*
 * Copyright (C) 2026 ArielOS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package com.android.providers.blockednumber;

import android.test.AndroidTestCase;

import androidx.test.filters.SmallTest;

/** ArielOS: senders that "block unknown SMS" must let through. */
@SmallTest
public class ArielSmsSenderTest extends AndroidTestCase {

    public void testNamedSendersPass() {
        assertTrue(BlockedNumberProvider.isNamedOrShortSender("Google"));
        assertTrue(BlockedNumberProvider.isNamedOrShortSender("Moja Banka"));
        assertTrue(BlockedNumberProvider.isNamedOrShortSender("InfoSMS1"));
    }

    public void testShortCodesPass() {
        assertTrue(BlockedNumberProvider.isNamedOrShortSender("1234"));
        assertTrue(BlockedNumberProvider.isNamedOrShortSender("123456"));
    }

    public void testPhoneNumbersDoNotPass() {
        assertFalse(BlockedNumberProvider.isNamedOrShortSender("1234567"));
        assertFalse(BlockedNumberProvider.isNamedOrShortSender("+381641234567"));
        assertFalse(BlockedNumberProvider.isNamedOrShortSender("064 123 4567"));
        // short but international: not a short code
        assertFalse(BlockedNumberProvider.isNamedOrShortSender("+12345"));
    }
}
