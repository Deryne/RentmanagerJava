<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Modifier un client
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post">
                            <div class="box-body">
                             <div class="form-group">
                                    <label for="user" class="col-sm-2 control-label">Client a modifier</label>

                                    <div class="col-sm-10">
                                    
                                        <select class="form-control" id="user" name="user">
		                                <c:forEach items="${listUsers}" var="user">
		                                    <option value="${user.id}">
		                                    	id :${user.id} Nom :${user.lastname} Prenom :${user.name} Date de naissance :${user.birthdate}
		                                    </option>
		                                </c:forEach>
                                        </select>
                                        
                                    </div>
                                </div>
                                
                            <div class="form-group">
                                    <label for="last_name" class="col-sm-2 control-label">Modifier le Nom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Nom">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="first_name" class="col-sm-2 control-label">Modifier le Prenom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="first_name" name="first_name" placeholder="Prenom">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Modifier l'Email</label>

                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                                    </div>
                                </div>
                                
                               <div class="form-group">
                                    <label for="birth" class="col-sm-2 control-label">Modifier la Date de naissance</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="birth" name="birth"
                                        required data-inputmask="'alias': 'dd/mm/yyyy'" data-mask>
                                    </div>
                                </div>
                                
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right">Modifier</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script>
    $(function () {
        $('[data-mask]').inputmask()
    });
</script>
</body>
</html>
