/*
 * Copyright 2018 Fraunhofer Institute SCAI, St. Augustin, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.fraunhofer.scai.bio.util;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.fraunhofer.scai.bio.Document;

/**
 * Renders a {@link Document} to a HTML document
 *
 * @author marc
 */
public class DocumentHTMLRendererTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Document document;
    private Document docNewDate;

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        File inputDir = new File(getClass().getResource("/positiveIn/testAnnotatedDocument.json").getPath());
        File newDate = new File(getClass().getResource("/positiveIn/testDocumentNewDateFormat.json").getPath());


        // create document from file
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        LocalDateTimeDeserializer dateTimeDeserializer = new LocalDateTimeDeserializer(formatter);
        LocalDateTimeSerializer dateTimeSerializer = new LocalDateTimeSerializer(formatter);

        JavaTimeModule javaTimeModule = new JavaTimeModule(); 
        javaTimeModule.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
        javaTimeModule.addSerializer(LocalDateTime.class, dateTimeSerializer);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(javaTimeModule);
        
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.findAndRegisterModules();
        
        System.out.println(mapper.getRegisteredModuleIds());
        System.out.println(mapper.getSerializationConfig().getDateFormat());
        document = mapper.readValue(inputDir.getAbsoluteFile(), Document.class);
        System.out.println(document.toJsonString());
        System.out.println("LOCALDATE " + document.getDocumentElement().getMetaElement().getBibliographic().getPubDate().toString());
        
        Date d = new Date(275997800036L);
        System.out.println("DATE " + d);
        
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(946594800598L);
        System.out.println("CAL " + cal.getTime());
//        LocalDate ld = LocalDate.ofEpochDay(cal.getTimeInMillis());
//        System.out.println("LD " + ld.toString());
        
        docNewDate = mapper.readValue(newDate.getAbsoluteFile(), Document.class);
    }

    /**
     * Test method for
     * {@link de.fraunhofer.scai.bio.util.DocumentHTMLRenderer#renderHTML()}.
     *
     * @throws Exception
     */
    @Test
    public void renderHTMLTest() {
        String html = DocumentHTMLRenderer.renderHTML(document);
        System.out.println(html);
    }


}
