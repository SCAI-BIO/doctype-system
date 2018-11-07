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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.fraunhofer.scai.bio.Document;

/**
 * Renders a {@link Document} to a HTML document
 * 
 * @author marc
 *
 */
public class DocumentHTMLRendererTest {
	
  private Document document;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
  	
		File inputDir = new File(getClass().getResource("/positiveIn/").getPath());

  	
		// create document from file 
    ObjectMapper mapper = new ObjectMapper();
    document = mapper.readValue(new File(inputDir.getPath() + "/" + "testAnnotatedDocument.json"), Document.class);
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
