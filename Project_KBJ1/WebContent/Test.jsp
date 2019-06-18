<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
    function googleSectionalElementInit() {
        new google.translate.SectionalElement({
            sectionalNodeClassName: 'goog-trans-section',
            controlNodeClassName: 'goog-trans-control',
            background: '#78E7FF'
        }, 'google_sectional_element');
    }
</script>
<script src="//translate.google.com/translate_a/element.js?cb=googleSectionalElementInit&ug=section&hl=ko"></script>

<div id="google_sectional_element" style="display:none"></div>

<div class="goog-trans-section">
    <div class="goog-trans">
        <div class="goog-trans-control"></div>
        <div class="goog-trans-info">[번역]을 누르시면 번역이 됩니다.</div>
    </div>
</body>
</html>