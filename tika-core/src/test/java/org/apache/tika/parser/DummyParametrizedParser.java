/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tika.parser;

import org.apache.tika.config.Field;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * A test Parsers to test {@link Field}
 * @since Apache Tika 1.14
 */
public class DummyParametrizedParser extends AbstractParser
        implements ConfigurableParser {

    private static Set<MediaType> MIMES = new HashSet<>();
    static {
        MIMES.add(MediaType.TEXT_PLAIN);
        MIMES.add(MediaType.TEXT_HTML);
        MIMES.add(MediaType.APPLICATION_XML);
        MIMES.add(MediaType.OCTET_STREAM);
    }

    @Field(name = "testparam") private String testParam;
    @Field private short xshort;
    @Field private int xint;
    @Field private long xlong;
    @Field(name = "xbigint") private BigInteger xbigInt;
    @Field private float xfloat;
    @Field private double xdouble;
    @Field private boolean xbool;
    @Field private URL xurl;
    @Field private URI xuri;

    @Field private String missing = "default";

    private String inner = "inner";
    private File xfile;

    @Field
    public void setXfile(File xfile){
        this.xfile = xfile;
    }

    @Override
    public Set<MediaType> getSupportedTypes(ParseContext context) {

        return MIMES;
    }

    @Override
    public void parse(InputStream stream, ContentHandler handler,
                      Metadata metadata, ParseContext context)
            throws IOException, SAXException, TikaException {

        metadata.add("testparam", testParam);
        metadata.add("xshort", xshort + "");
        metadata.add("xint", xint + "");
        metadata.add("xlong", xlong + "");
        metadata.add("xbigint", xbigInt + "");
        metadata.add("xfloat", xfloat + "");
        metadata.add("xdouble", xdouble + "");
        metadata.add("xbool", xbool + "");
        metadata.add("xuri", xuri + "");
        metadata.add("xurl", xurl + "");
        metadata.add("xfile", xfile + "");

        metadata.add("inner", inner + "");
        metadata.add("missing", missing + "");
    }
}