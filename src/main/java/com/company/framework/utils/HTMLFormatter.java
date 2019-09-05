package com.company.framework.utils;

import java.text.SimpleDateFormat;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import org.openqa.selenium.logging.LogEntry;

/**
 * This Class adds the html format for the log file.
 */
public class HTMLFormatter extends java.util.logging.Formatter {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public String format(LogEntry entry) {
        String message = "<tr><td>" + format.format(entry.getTimestamp()) + "</td><td>";
        message = message + entry.getMessage();
        message = message + "</td><td>" + entry.getLevel() + "</td></tr>\n";
        return message;
    }

    @Override
    public String format(LogRecord record) {
        return "";
    }

    public String getHead(Handler h) {
        return ("<html>\n  <body>\n" + "<style>.logTable {    margin:0px;padding:0px;     width:100%;     box-shadow: 10px 10px 5px #888888;  border:1px solid #000000;   -moz-border-radius-bottomleft:0px;  -webkit-border-bottom-left-radius:0px;  border-bottom-left-radius:0px;  -moz-border-radius-bottomright:0px;     -webkit-border-bottom-right-radius:0px;     border-bottom-right-radius:0px;     -moz-border-radius-topright:0px;    -webkit-border-top-right-radius:0px;    border-top-right-radius:0px;    -moz-border-radius-topleft:0px;     -webkit-border-top-left-radius:0px;     border-top-left-radius:0px; }.logTable table{     border-collapse: collapse;         border-spacing: 0;    width:100%;     height:100%;    margin:0px;padding:0px; }.logTable tr:last-child td:last-child {   -moz-border-radius-bottomright:0px;     -webkit-border-bottom-right-radius:0px;     border-bottom-right-radius:0px; } .logTable table tr:first-child td:first-child {  -moz-border-radius-topleft:0px;     -webkit-border-top-left-radius:0px;     border-top-left-radius:0px; } .logTable table tr:first-child td:last-child {   -moz-border-radius-topright:0px;    -webkit-border-top-right-radius:0px;    border-top-right-radius:0px; }.logTable tr:last-child td:first-child{  -moz-border-radius-bottomleft:0px;  -webkit-border-bottom-left-radius:0px;  border-bottom-left-radius:0px; }.logTable tr:hover td{ } .logTable tr:nth-child(odd){ background-color:#aad4ff; } .logTable tr:nth-child(even)    { background-color:#ffffff; }.logTable td{    vertical-align:middle;  border:1px solid #000000;   border-width:0px 1px 1px 0px;   text-align:left;    padding:7px;    font-size:17px;     font-family:Arial;  font-weight:normal;     color:#000000; }.logTable tr:last-child td{    border-width:0px 1px 0px 0px; }.logTable tr td:last-child{     border-width:0px 0px 1px 0px; }.logTable tr:last-child td:last-child{  border-width:0px 0px 0px 0px; } .logTable tr:first-child td{       background:-o-linear-gradient(bottom, #005fbf 5%, #003f7f 100%);    background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #005fbf), color-stop(1, #003f7f) );    background:-moz-linear-gradient( center top, #005fbf 5%, #003f7f 100% );    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#005fbf', endColorstr='#003f7f');  background: -o-linear-gradient(top,#005fbf,003f7f);     background-color:#005fbf;   border:0px solid #000000;   text-align:center;  border-width:0px 0px 1px 1px;   font-size:18px;     font-family:Arial;  font-weight:bold;   color:#ffffff; } .logTable tr:first-child:hover td{    background:-o-linear-gradient(bottom, #005fbf 5%, #003f7f 100%);    background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #005fbf), color-stop(1, #003f7f) );    background:-moz-linear-gradient( center top, #005fbf 5%, #003f7f 100% );    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#005fbf', endColorstr='#003f7f');  background: -o-linear-gradient(top,#005fbf,003f7f);     background-color:#005fbf; } .logTable tr:first-child td:first-child{   border-width:0px 0px 1px 0px; } .logTable tr:first-child td:last-child{    border-width:0px 0px 1px 1px; }</style>"
                +"<div class=\"logTable\"><Table>\n<tr><th width=\"20%\">Time</th><th width=\"70%\">Log Message</th><th width=\"10%\">Log Level</th></tr>\n");
    }

    public String getTail(Handler h) {
        return ("</table></div>\n</body>\n</html>");
    }
}



