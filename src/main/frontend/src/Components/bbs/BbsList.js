import "../../css/bbslist.css";
import "../../css/page.css";

import React, {useEffect, useState} from "react";

//Pagination
import Pagination from "react-js-pagination";

import axios from "axios";
import {Link} from "react-router-dom";

function BbsList() {
    //BbsList
    const [bbsList, setBbsList] = useState([]);

    //검색용 Hook
    //게시글 조회
    const [choiceVal, setChoiceVal] = useState("");
    const [searchVal, setSearchVal] = useState("");

    //Paging
    const [page, setPage] = useState(1);
    const [pageSize, setSizePage] = useState(10);
    const [totalPages, setTotalPages] = useState(0);
    const [totalCnt, setTotalCnt] = useState(0);

    const getBbslist = async (page) => {
        try {
            const response = await axios.get("http://localhost:8989/board/list", {params:{"page": page -1},
        });
          console.log("[BbsList.js] getBbslist() success :D");
          console.log(response.data);

          setBbsList(response.data.content);
          setPageSize(response.data.pageSize);
          setTotalPages(response.data.totalPages);
          setTotalCnt(response.data.totalElements);

        } catch (err) {
          console.log("[BbsList.js] getBbslist() error :<");
          console.log(err);
        }
    };

    //게시글 검색
    const search = async() => {
        try {
            const response = await axios.get("http://localhost:8989/board/search", {
                params:{
                    page: page -1,
                    title: choiceVal === "title" ? searchVal : "",
                    content: choiceVal === "content" ? searchVal : "",
                    writerName: choiceVal === "writerName" ? searchVal : "",
                },
        });
        console.log("[BbsList.js] searchBtn() success: D");
        console.log(response.data);

        setBbsList(response.data.content);
        setPageSize(response.data.pageSize);
        setTotalPages(response.data.totalPages);
        setTotalCnt(response.data.totalElements);

        } catch(error) {
            console.log("[BbsList.js] searchBtn(0 error : <");
            console.log(error);
        }
    };

    //첫 로딩시, 한 페이지만 가져오기
    useEffect(() => {
        getBbsList(1);
    }, []);

    //검색 -> 검색 조건 저장
    const changeChoice = (event) => {setChoiceVal(event.target.value);};
    const changeSearch = (event) => {setSearchVal(event.target.value);};

    //페이징 보여주기
    const changePage = (page) => {
        setPage(page);
        getBbsList(page);
    };



    //HTML
    return(
        <div>
            {/* 검색 */}
            <table className="search">
                <tbody>
                    <tr>
                        <td>
                            <td>
                                <select
                                    className="custom-select"
                                    value={choiceVal}
                                    onChange={changeChoice}
                                >
                                    <option>검색 옵션 선택</option>
                                    <option value="title">제목</option>
                                    <option value="content">내용</option>
                                    <option value="writer">작성자</option>
                                </select>
                            </td>
                        </td>

                        <td>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="검색어"
                                value={searchVal}
                                onChange={changeSearch}
                            />
                        </td>

                        <td>
                            <button
                                type="button"
                                className="btn btn-outline-secondary"
                                onClick={search}
                            >
                            <i className="fas fa-search"></i> 검색
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br />

            <table className="table table-hover">
                <thead>
                    <tr>
                        <th className="col-1">번호</th>
                        <th className="col-7">제목</th>
                        <th className="col-3">작성자</th>
                        <th className="col-1">조회수</th>
                    </tr>
                </thead>

                <tbody>
                    {/* 데이터 */}
                </tbody>
            </table>

            <Pagination
                className="pagination"
                activePage={page}
                itemsCountPerPage={pageSize}
                totalItemsCount={totalPages}
                prevPageText={"<"}
                nextPageText={">"}
                onChange={changePage}
            />

            {/* 글쓰기 버튼/링크/아이콘 */}
            {/* /bbswrite */}
            <div className="my-5 d-flex justify-content-center">
                <Link className="btn btn-outline-secondary" to="/bbswrite">
                <i className="fas fa-pen"></i> &nbsp; 글쓰기
                </Link>
            </div>

        </div>
    );

}
export default BbsList;