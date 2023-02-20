<#import "template.ftl" as layout>
<@layout.registrationLayout displayInfo=social.displayInfo; section>
    <#if section = "title">
        ${msg("loginTitle",(realm.displayName!''))}
    <#elseif section = "header">
       
        <script>
            function togglePassword() {
                var x = document.getElementById("password");
                var v = document.getElementById("vi");
                if (x.type === "password") {
                    x.type = "text";
                    v.src = "${url.resourcesPath}/img/eye.png";
                } else {
                    x.type = "password";
                    v.src = "${url.resourcesPath}/img/eye-off.png";
                }
            }
        </script>
    <#elseif section = "form">
        <div>
            <img class="sider-bg" src="${url.resourcesPath}/img/sider-bg.png" alt="M-Go">
        </div>
        <div class="box-container">
            <div>
                <p class="application-name title2">${msg("loginTitle")}</p>
            </div>
        <#if realm.password>
            <div class="form-container">
               <form id="kc-form-login" class="form" onsubmit="return true;" action="${url.loginAction}" method="post">
                     <div class="login-field-group">
                        <label for="username" class="login-field-group-label">${msg("usernameOrEmail")}</label>  
                         <input id="username" class="login-field" type="text" name="username" tabindex="1">
                    </div> 
                     <div class="login-field-group">
                        <label for="password" class="login-field-group-label">${msg("password")}</label>      
                        <input id="password" class="login-field" type="password" name="password" tabindex="2">

                </div>

                <div class="${properties.kcFormGroupClass!} ${properties.kcFormSettingClass!} kc-form-options-container">
                    <div id="kc-form-options">
                        <#if realm.rememberMe && !usernameHidden??>
                            <div class="checkbox">
                                <label>
                                    <#if login.rememberMe??>
                                        <input tabindex="3" id="rememberMe" name="rememberMe" type="checkbox" checked> ${msg("rememberMe")}
                                    <#else>
                                        <input tabindex="3" id="rememberMe" name="rememberMe" type="checkbox"> ${msg("rememberMe")}
                                    </#if>
                                </label>
                            </div>
                        </#if>
                        </div>
                        <div class="${properties.kcFormOptionsWrapperClass!}">
                            <#if realm.resetPasswordAllowed>
                                <span><a tabindex="5" href="${url.loginResetCredentialsUrl}">${msg("doForgotPassword")}</a></span>
                            </#if>
                        </div>

                  </div>
                <input class="submit" type="submit" value="${msg("doLogIn")}" tabindex="3">
                 <#if realm.password && realm.registrationAllowed && !registrationDisabled??>
                    <div id="kc-registration-container">
                        <div id="kc-registration">
                            <span>${msg("noAccount")} <a tabindex="6"
                                                        href="${url.registrationUrl}">${msg("doRegister")}</a></span>
                        </div>
                    </div>
                </#if>
                
                </form>
            </div>
        </#if>
        <#if social.providers??>
            <p class="para">${msg("selectAlternative")}</p>
            <div id="social-providers">
                <#list social.providers as p>
                <input class="social-link-style" type="button" onclick="location.href='${p.loginUrl}';" value="${p.displayName}"/>
                </#list>
            </div>
        </#if>
    </#if>

</@layout.registrationLayout>
