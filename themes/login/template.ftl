<#macro registrationLayout bodyClass="" displayInfo=false displayMessage=true>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="robots" content="noindex, nofollow">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,200..900;1,700" rel="stylesheet"/>
    <link href="${url.resourcesPath}/img/favicon.png" rel="icon"/>

    <title><#nested "title"></title>
    <#if properties.styles?has_content>
        <#list properties.styles?split(' ') as style>
            <link href="${url.resourcesPath}/${style}" rel="stylesheet" />
        </#list>
    </#if>
</head>

	<body>
        <div class="login-content">

            <div class="box">
            
            <div class="app-sider">
            <img class="sider-bg" src="${url.resourcesPath}/img/sider-bg.png" alt="M-Go">
            </div>
            <div class="app-main">
                <div class="logo-container">
                     <img class="sider-bg" src="${url.resourcesPath}/img/logo.png" alt="M-Go">
                </div>
                <div class="header-container title2">
                    <#nested "header">
                </div>
                <div class="form-container">    
                    <#nested "form">
                    <#nested "info">
                </div>
                <#if displayMessage && message?has_content>
                    <div class="alert alert-${message.type}">
                        <#if message.type = 'success'><span class="${properties.kcFeedbackSuccessIcon!}"></span></#if>
                        <#if message.type = 'warning'><span class="${properties.kcFeedbackWarningIcon!}"></span></#if>
                        <#if message.type = 'error'><span class="${properties.kcFeedbackErrorIcon!}"></span></#if>
                        <#if message.type = 'info'><span class="${properties.kcFeedbackInfoIcon!}"></span></#if>
                        <span class="message-text">${message.summary?no_esc}</span>
                    </div>
                </#if>
            </div>

            </div> 

              
        </div>
             

        <div>
            <p class="copyright">&copy; ${msg("copyright", "${.now?string('yyyy')}")}</p>
        </div>
	</body>
</html>
</#macro>
