/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.vault.authentication;

import org.springframework.util.Assert;

import javax.annotation.Nullable;

/**
 * @author Mikhael Sokolov
 */
public class UsernamePasswordAuthenticationOptions {

    public static final String DEFAULT_USERPASS_AUTHENTICATION_PATH = "userpass";

    /**
     * Path of the userpass authentication backend mount.
     */
    private final String path;

    /**
     * Username of the userpass authentication backend mount.
     */
    private final String username;

    /**
     * Password of the userpass authentication backend mount.
     */
    private final CharSequence password;

    private UsernamePasswordAuthenticationOptions(String username, CharSequence password, String path) {
        this.username = username;
        this.password = password;
        this.path = path;
    }

    public String getUsername() {
        return username;
    }

    public CharSequence getPassword() {
        return password;
    }

    public String getPath() {
        return path;
    }

    public static UsernamePasswordAuthenticationBuilder builder() {
        return new UsernamePasswordAuthenticationBuilder();
    }

    public static class UsernamePasswordAuthenticationBuilder {

        @Nullable
        private String username;

        @Nullable
        private CharSequence password;

        private String path = DEFAULT_USERPASS_AUTHENTICATION_PATH;

        UsernamePasswordAuthenticationBuilder() {
        }

        public UsernamePasswordAuthenticationBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UsernamePasswordAuthenticationBuilder password(CharSequence password) {
            this.password = password;
            return this;
        }

        public UsernamePasswordAuthenticationBuilder path(String path) {
            this.path = path;
            return this;
        }

        public UsernamePasswordAuthenticationOptions build() {
            Assert.notNull(this.username, "Username must not be null");
            Assert.notNull(this.password, "Password must not be null");

            return new UsernamePasswordAuthenticationOptions(username, password, path);
        }
    }
}
