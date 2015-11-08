<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>3F - Foresight and Fire Fighting</title>
        
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- Styles -->
        <link href="styles/bootstrap.css" rel="stylesheet">
        <style>
          body { padding-top: 60px; /* 60px to make the container go all the way
          to the bottom of the topbar */ }
        </style>
        <link href="styles/bootstrap-responsive.css" rel="stylesheet">
        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js">
          </script>
        <![endif]-->
        <!-- Le fav and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
        
        <link href="styles/fireWaterLineContent.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js">
        </script>
        <script src="scripts/bootstrap.js">
        </script>

    </head>
    <body>
        <div class="navbar navbar-fixed-top navbar-inverse">
        <div class="navbar-inner">
          <div class="container-fluid">
            <a class="brand" href="index.jsp">
              3F Dashboard
            </a>
            <div class="nav-collapse">
              <ul class="nav">
                <li>
                  <a href="index.jsp">
                    Home
                  </a>
                </li>
                <li>
                  <a href="#">
                    Run
                  </a>
                </li>
              </ul>
              <ul class="nav pull-right">
                  <li><a href="#">Login</a></li>
                          <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown">Options <b class="caret"></b></a>
                      <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                      </ul>
                  </li>
                  </ul>
            </div>
            <form class="navbar-form pull-right">
            </form>
          </div>
        </div>
      </div>
      <div class="container-fluid">
        <div class="row">
          <div class="span2" style="width:205px;">
            <div class="well sidebar-nav">
              <ul class="nav nav-list">
                <li class="nav-header">
                  <p>
                    <strong>
                      Actions
                    </strong>
                  </p>
                </li>
                <li>
                </li>
                <li class="">
                  <a href="#">
                    Provide fire and water lines
                  </a>
                  <a href="#">
                    Select the fire area
                  </a>
                </li>
                <li class="">
                  <a href="#">
                    Fire Simulation
                  </a>
                </li>
                <li class="">
                  <a href="#">
                    Advanced Simulation
                  </a>
                </li>
                <li class="nav-header margin10px">
                  <p>
                    <strong>
                      Visualization
                    </strong>
                  </p>
                </li>
                <li>
                </li>
                <div>
                </div>
                <div>
                </div>
                <li class="">
                  <a href="wind.jsp">
                    Wind Speed
                  </a>
                </li>
                <li class="">
                  <a href="topology.jsp">
                    Topology
                  </a>
                </li>
                <li class="">
                  <a href="#">
                    Fire Line
                  </a>
                </li>
                <li class="">
                  <a href="smokePlume.jsp">
                    2D/3D Smoke Plume
                  </a>
                </li>
                <li class="">
                  <a href="#">
                    Individual Exposure
                  </a>
                </li>
              </ul>
            </div>
          </div>
          <div class="span11">
            <ul class="breadcrumb">
              <li class="">
                <a href="index.jsp">
                  Home
                </a>
              </li>
            <span class="divider">
              /
            </span>
            <li class="">
              <a href="FireWaterLine.jsp">
                Provide fire and water lines
              </a>
            </li>
            </ul>
            <div class="row-fluid">
              <div class="span7">
                <h4>
                  Fire Line
                </h4>
                <div class="map-topo">
                </div>
                <div class="btn-toolbar">
                  <div class="btn-group">
                    <a class="btn" href="#">
                      +
                    </a>
                    <a class="btn mr" href="#">
                      -
                    </a>
                  </div>
                </div>
              </div>
            </div>
            <hr>
            <div>
              © 3F - Foresight and Fire Fighting 2012
            </div>
          </div>
        </div>
      </div>
        
    </body>
</html>
