<%
StringBuilder sb = new StringBuilder();
sb.append(request.getScheme());
sb.append("://").append(request.getServerName());
int port = request.getServerPort();
if (port != 80) {
	sb.append(":").append(port);
}
sb.append(request.getContextPath()).append("/");
String webRoot=sb.toString();
String sid = String.valueOf(session.getAttribute("sid"));
String sessionId = session.getId();
String serverName = request.getContextPath();
String userName = String.valueOf(session.getAttribute("name")); 
%>
<script type="text/javascript">
var WEBROOT="<%=webRoot%>",sid = "<%=sid%>",sessionId = "<%=sessionId%>",SERVERNAME="<%=serverName%>",userName="<%=userName%>";
</script>
