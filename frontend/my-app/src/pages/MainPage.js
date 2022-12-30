import {ThemeProvider} from "@mui/material";
import {Theme} from "../Theme/Theme"
import MenuAppBarComponent from "../components/appbar/MenuAppBarComponent";

const MainPage = () => {
  return (
    <>
      <ThemeProvider theme={Theme}>
      <MenuAppBarComponent/>
      </ThemeProvider>
    </>
  )
}

export default MainPage