 /*
 *  Copyright Beijing 58 Information Technology Co.,Ltd.
 *
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package com.bj58.spat.hades.interceptor;

import com.bj58.spat.hades.ActionResult;
import com.bj58.spat.hades.BeatContext;

/**
 * 拦截器需要实现的接口
 * 
 * @author Service Platform Architecture Team (spat@58.com)
 *
 */
public interface ActionInterceptor {
	
	/**
	 * 拦截当前请求
	 * @param beat 当前请求的上下文
	 * @return 
	 * null，进入下一个拦截或执行Action
	 * <BR/>
	 * 非空，直接显示，不进入下一个拦截或执行Action
	 */
	public ActionResult preExecute(BeatContext beat);
}
