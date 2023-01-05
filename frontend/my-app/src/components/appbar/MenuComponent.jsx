import React from "react";
import AppBarComponent from "./AppBarComponent";
import { SidebarContext } from "./SidebarContext";
import { Sidebar, Menu, MenuItem, useProSidebar } from "react-pro-sidebar";
import SidebarComponent from "./SidebarComponent";

const MenuComponent = () => {
  const { collapseSidebar, toggleSidebar, toggled } = useProSidebar();

  const toggle = () => {
    toggleSidebar();
    if (toggled) {
      console.log(true);
      collapseSidebar();
    } else {
      console.log(false);
      collapseSidebar();
    }
  };

  return (
    <>
      <SidebarContext.Provider value={toggle}>
        <AppBarComponent />
        <SidebarComponent />
      </SidebarContext.Provider>
    </>
  );
};

export default MenuComponent;
