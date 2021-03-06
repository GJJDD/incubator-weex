/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.taobao.weex.ui.component;

import com.taobao.weappplus_sdk.BuildConfig;
import com.taobao.weex.WXSDKInstanceTest;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.TestDomObject;
import com.taobao.weex.ui.SimpleComponentHolder;
import com.taobao.weex.ui.view.WXEditText;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.InvocationTargetException;

import static com.taobao.weex.common.Constants.Name.*;
import static com.taobao.weex.common.Constants.Value.*;

/**
 * Created by sospartan on 8/3/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19)
@PowerMockIgnore( {"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest
public class EditComponentTest {
  static final String[] PROPS = {
      TEXT_ALIGN,
      FONT_SIZE,
      COLOR,
      TYPE,
      VALUE,
      PLACE_HOLDER,
      PLACEHOLDER_COLOR,
      AUTOFOCUS,
      LINES,
      SINGLELINE,
      MAX_LENGTH,
      ROWS};
  static final Object[][] TEST_VALUES = {
      {null,CENTER, Constants.Name.LEFT, Constants.Name.RIGHT,"kdkdkdk"},
      {null,12,Integer.MAX_VALUE,0,-2},
      {"red","#000","#ffffff","rgb(12,23,45)"},
      {"DKDK",
          TEXT,
          PASSWORD,
          TEL,
          EMAIL,
          URL,
          DATE,
          TIME,
          DATETIME},
      {null,123,"dkdkdkdk"},
      {null,123,"dkdkdkdk"},
      {"red","#000","#ffffff","rgb(12,23,45)"},
      {null,true,"true","false",false,"test"},
      {null,34,Integer.MAX_VALUE,-1,"test"},
      {null,true,"true","false",false,"test"},
      {null,34,Integer.MAX_VALUE,-1,"test"},
      {null,34,Integer.MAX_VALUE,-1,"test"},
      };

  AbstractEditComponent component;

  public static WXInput create() throws IllegalAccessException, InstantiationException, InvocationTargetException {
    return (WXInput) new SimpleComponentHolder(WXInput.class).createInstance(WXSDKInstanceTest.createInstance(), new TestDomObject(), WXDivTest.create());
  }

  public static Textarea createTextarea() throws IllegalAccessException, InstantiationException, InvocationTargetException {
    return (Textarea) new SimpleComponentHolder(Textarea.class).createInstance(WXSDKInstanceTest.createInstance(), new TestDomObject(), WXDivTest.create());
  }


  @Before
  public void setUp() throws Exception {
    component = create();
    ComponentTest.create(component);
  }

  @After
  public void tearDown() throws Exception {
    ComponentTest.destory(component);
  }

  @Test
  public void testEvent() throws Exception {
    WXEditText view = component.getHostView();
    view.performClick();
    view.setText("");
    view.requestFocus();
    view.setText("hello");
    view.clearFocus();
    view.setText(null);
  }

  @Test
  public void testSetProperty() throws Exception {

    ComponentTest.setProperty(component,PROPS,TEST_VALUES);
  }

  @Test
  public void testFocus() throws Exception {
    component.getParent().mHost = component.getParent().initComponentHostView(component.getContext());
    component.getParent().interceptFocus();
    component.getHostView().clearFocus();
    component.focus();
    Assert.assertEquals(component.getHostView().hasFocus(), true);
  }


}
