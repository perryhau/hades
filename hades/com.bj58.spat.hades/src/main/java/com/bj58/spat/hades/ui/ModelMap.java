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
package com.bj58.spat.hades.ui;


import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.bj58.spat.hades.utils.Assert;
import com.bj58.spat.hades.utils.Conventions;



/**
 * 通过扩展LinkedHashMap来实现对数据存贮的封装。
 * 
 * @author Service Platform Architecture Team (spat@58.com)
 */
public class ModelMap extends LinkedHashMap<String, Object> {

	/**
	 * Construct a new, empty <code>ModelMap</code>.
	 */
	public ModelMap() {
	}

	/**
	 * Construct a new <code>ModelMap</code> containing the supplied attribute
	 * under the supplied name.
	 * @see #addAttribute(String, Object)
	 */
	public ModelMap(String attributeName, Object attributeValue) {
		addAttribute(attributeName, attributeValue);
	}

	/**
	 * Construct a new <code>ModelMap</code> containing the supplied attribute.
	 * Uses attribute name generation to generate the key for the supplied model
	 * object.
	 * @see #addAttribute(Object)
	 */
	public ModelMap(Object attributeValue) {
		addAttribute(attributeValue);
	}


	/**
	 * Add the supplied attribute under the supplied name.
	 * @param attributeName the name of the model attribute (never <code>null</code>)
	 * @param attributeValue the model attribute value (can be <code>null</code>)
	 */
	public ModelMap addAttribute(String attributeName, Object attributeValue) {
		Assert.notNull(attributeName, "Model attribute name must not be null");
		put(attributeName, attributeValue);
		return this;
	}

	/**
	 * Add the supplied attribute to this <code>Map</code> using a
	 * {@link org.springframework.core.Conventions#getVariableName generated name}.
	 * <p><emphasis>Note: Empty {@link Collection Collections} are not added to
	 * the model when using this method because we cannot correctly determine
	 * the true convention name. View code should check for <code>null</code> rather
	 * than for empty collections as is already done by JSTL tags.</emphasis>
	 * @param attributeValue the model attribute value (never <code>null</code>)
	 */
	public ModelMap addAttribute(Object attributeValue) {
		Assert.notNull(attributeValue, "Model object must not be null");
		if (attributeValue instanceof Collection && ((Collection) attributeValue).isEmpty()) {
			return this;
		}
		return addAttribute(Conventions.getVariableName(attributeValue), attributeValue);
	}

	/**
	 * Copy all attributes in the supplied <code>Collection</code> into this
	 * <code>Map</code>, using attribute name generation for each element.
	 * @see #addAttribute(Object)
	 */
	public ModelMap addAllAttributes(Collection<?> attributeValues) {
		if (attributeValues != null) {
			for (Object attributeValue : attributeValues) {
				addAttribute(attributeValue);
			}
		}
		return this;
	}

	/**
	 * Copy all attributes in the supplied <code>Map</code> into this <code>Map</code>.
	 * @see #addAttribute(String, Object)
	 */
	public ModelMap addAllAttributes(Map<String, ?> attributes) {
		if (attributes != null) {
			putAll(attributes);
		}
		return this;
	}

	/**
	 * Copy all attributes in the supplied <code>Map</code> into this <code>Map</code>,
	 * with existing objects of the same name taking precedence (i.e. not getting
	 * replaced).
	 */
	public ModelMap mergeAttributes(Map<String, ?> attributes) {
		if (attributes != null) {
			for (String key : attributes.keySet()) {
				if (!containsKey(key)) {
					put(key, attributes.get(key));
				}
			}
		}
		return this;
	}

	/**
	 * Does this model contain an attribute of the given name?
	 * @param attributeName the name of the model attribute (never <code>null</code>)
	 * @return whether this model contains a corresponding attribute
	 */
	public boolean containsAttribute(String attributeName) {
		return containsKey(attributeName);
	}


	/**
	 * @deprecated as of Spring 2.5, in favor of {@link #addAttribute(String, Object)}
	 */
	@Deprecated
	public ModelMap addObject(String modelName, Object modelObject) {
		return addAttribute(modelName, modelObject);
	}

	/**
	 * @deprecated as of Spring 2.5, in favor of {@link #addAttribute(Object)}
	 */
	@Deprecated
	public ModelMap addObject(Object modelObject) {
		return addAttribute(modelObject);
	}

	/**
	 * @deprecated as of Spring 2.5, in favor of {@link #addAllAttributes(Collection)}
	 */
	@Deprecated
	public ModelMap addAllObjects(Collection objects) {
		return addAllAttributes(objects);
	}

	/**
	 * @deprecated as of Spring 2.5, in favor of {@link #addAllAttributes(Map)}
	 */
	@Deprecated
	public ModelMap addAllObjects(Map objects) {
		return addAllAttributes(objects);
	}
}
