/**
 * Copyright (c) 2012-2015, jcabi.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the jcabi.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jcabi.log;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * ParseableInformation test case.
 * @author Jose V. Dal Pra Junior (jrdalpra@gmail.com)
 * @version $Id$
 * @since 0.18
 */
public class ParseableInformationTest {

    /**
     * White color key.
     */
    private static final String WHITE = "white";

    /**
     * ParseableInformation can parse if the information correctly if is using
     * the right pattern.
     */
    @Test
    @SuppressWarnings("PMD.UseConcurrentHashMap")
    public final void parsesTheInformationCorrectly() {
        final Map<String, String> parsed = new ParseableInformation(
            "white:10,black:20"
        ).parse();
        Assert.assertThat(parsed, Matchers.hasEntry(WHITE, "10"));
        Assert.assertThat(parsed, Matchers.hasEntry("black", "20"));
    }

    /**
     * ParseableInformation can throw an an exception when parsing wrong info.
     */
    @Test
    public final void throwsAnExceptionWhenParsingSomethingWrong() {
        try {
            new ParseableInformation(WHITE).parse();
            Assert.fail("Should never enter this assert!");
        } catch (final IllegalStateException ex) {
            Assert.assertThat(
                ex.getMessage(), Matchers.equalTo(
                    String.format(
                        StringUtils.join(
                            "Information is not using the pattern ",
                            "KEY1:VALUE,KEY2:VALUE %s"
                        ), WHITE
                    )
                )
            );
        }
    }

}