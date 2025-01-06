import {createContext, useState} from "react";

export const HttpHeaderContext = createContext();

function HttpHeaderProvider({children}) {
    const [headers, setHeaders] = useState({
        //새로고침하면 App Context 사라지기 때문에, 초기 값은 localStorage 값으로 세팅
        Authorization: `Bearer ${localStorage.getItem("bbs_access_token")`},
    });

    const value = {headers, setHeaders};

    return (
        <HttpHeaderContext.Provider value={value}>
            {children}
        </HttpHeaderContext.Provider>
    );

}
export default HttpHeaderProvider;