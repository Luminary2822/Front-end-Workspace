function onclick()
{
    var tab1=document.getElementById("tab1");
    var tab2=document.getElementById("tab2");
    var code=document.getElementById("QR_code");
    var form_c=document.getElementById("form_content");
    tab2.style.border = '0px';
    tab1.style.borderBottom='3px solid #C50001';
    code.style.display="none";
    form_c.style.display="block";
}
function onclick2()
{
    var tab1=document.getElementById("tab1");
    var tab2=document.getElementById("tab2");
    var code=document.getElementById("QR_code");
    var form_c=document.getElementById("form_content");
    tab1.style.border = '0px';
    tab2.style.borderBottom='3px solid #C50001';
    code.style.display="block";
    form_c.style.display="none";
}

