import React from "react";
import AppBarComponent from "./AppBarComponent";
import { SidebarContext } from "./SidebarContext";
import { useProSidebar } from "react-pro-sidebar";

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
      </SidebarContext.Provider>
    </>
  );
};

export default MenuComponent;
