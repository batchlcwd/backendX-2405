import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import { RedocStandalone } from "redoc";

function App() {
  return (
    <div>
      <RedocStandalone
        specUrl="http://localhost:8081/v3/api-docs"
        options={{
          nativeScrollbars: true,
          theme: { colors: { primary: { main: "#dd5522" } } },
        }}
      />
    </div>
  );
}

export default App;
