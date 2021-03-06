/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.spring.web.servlet.tags;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.feilong.spring.web.util.WebSpringUtil;

/**
 * 条件父类标签,需要条件控制的(需要和业务层打交道).
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @see org.springframework.web.servlet.tags.RequestContextAwareTag
 * @since 1.0.0
 * @deprecated 建议不直接使用 {@link RequestContextAwareTag},使用feilong 原生的 {@link "BaseTag"},如果要想在tag里面得到 spring相关的bean ,可以单独使用
 *             {@link WebSpringUtil#getBean(HttpServletRequest, Class)}等方法
 */
@Deprecated
public abstract class AbstractSpringConditionalTag extends BaseSpringTag{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6162717594100732596L;

    /**
     * 标签开始.
     *
     * @return the int
     */
    @Override
    public int doStartTagInternal(){
        if (condition()){
            // 将body的内容输出到存在的输出流中 表示需要处理标签体,但绕过setBodyContent()和doInitBody()方法
            return EVAL_BODY_INCLUDE;
        }
        // 表示不用处理标签体,直接调用doEndTag()方法
        return SKIP_BODY;
    }

    /**
     * 标签结束.
     *
     * @return the int
     */
    @Override
    public int doEndTag(){
        return EVAL_PAGE;// 处理标签后,继续处理JSP后面的内容
    }

    /**
     * Condition.
     *
     * @return true, if condition
     */
    protected abstract boolean condition();
}
