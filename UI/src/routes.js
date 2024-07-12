/*!

=========================================================
* Black Dashboard React v1.2.2
=========================================================

* Product Page: https://www.creative-tim.com/product/black-dashboard-react
* Copyright 2023 Ishan Liyanage (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/black-dashboard-react/blob/master/LICENSE.md)

* Coded by Ishan Liyanage

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import Dashboard from "views/Dashboard.js";
import Facebook from "views/Facebook.js";
import Apple from "views/Apple.js";
import Google from "views/Google.js";
import Amazon from "views/Amazon.js";
import Netflix from "views/Netflix.js";

import Icons from "views/Icons.js";
import Map from "views/Map.js";
import Notifications from "views/Notifications.js";
import Rtl from "views/Rtl.js";
import TableList from "views/TableList.js";
import Typography from "views/Typography.js";
import UserProfile from "views/UserProfile.js";

var routes = [
  {
    path: "/dashboard",
    name: "Train The Model",
    rtlName: "لوحة القيادة",
    icon: "tim-icons icon-chart-bar-32",
    component: <Dashboard />,
    layout: "/admin",
  },
  {
    path: "/facebook",
    name: "Facebook",
    rtlName: "الرموز",
    icon: "tim-icons icon-atom",
    component: <Facebook />,
    layout: "/admin",
  },
  {
    path: "/apple",
    name: "Apple",
    rtlName: "الرموز",
    icon: "tim-icons icon-app",
    component: <Apple />,
    layout: "/admin",
  },
  {
    path: "/amazon",
    name: "Amazon",
    rtlName: "الرموز",
    icon: "tim-icons icon-bell-55",
    component: <Amazon />,
    layout: "/admin",
  },
  {
    path: "/netflix",
    name: "Netflix",
    rtlName: "الرموز",
    icon: "tim-icons icon-bulb-63",
    component: <Netflix />,
    layout: "/admin",
  },
  {
    path: "/google",
    name: "Google",
    rtlName: "الرموز",
    icon: "tim-icons icon-coins",
    component: <Google />,
    layout: "/admin",
  },
  // {
  //   path: "/map",
  //   name: "Map",
  //   rtlName: "خرائط",
  //   icon: "tim-icons icon-pin",
  //   component: <Map />,
  //   layout: "/admin",
  // },
  // {
  //   path: "/notifications",
  //   name: "Notifications",
  //   rtlName: "إخطارات",
  //   icon: "tim-icons icon-bell-55",
  //   component: <Notifications />,
  //   layout: "/admin",
  // },
  // {
  //   path: "/user-profile",
  //   name: "User Profile",
  //   rtlName: "ملف تعريفي للمستخدم",
  //   icon: "tim-icons icon-single-02",
  //   component: <UserProfile />,
  //   layout: "/admin",
  // },
  // {
  //   path: "/tables",
  //   name: "Table List",
  //   rtlName: "قائمة الجدول",
  //   icon: "tim-icons icon-puzzle-10",
  //   component: <TableList />,
  //   layout: "/admin",
  // },
  // {
  //   path: "/typography",
  //   name: "Typography",
  //   rtlName: "طباعة",
  //   icon: "tim-icons icon-align-center",
  //   component: <Typography />,
  //   layout: "/admin",
  // },
  // {
  //   path: "/rtl-support",
  //   name: "RTL Support",
  //   rtlName: "ار تي ال",
  //   icon: "tim-icons icon-world",
  //   component: <Rtl />,
  //   layout: "/rtl",
  // },
];
export default routes;
