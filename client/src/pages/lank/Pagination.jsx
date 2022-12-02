import React from 'react';
import styled from 'styled-components';
import prevIcon from '../../images/icons8-back-50 (2).png';

const PaginationComponent = styled.div`
  .pagination {
    width: 100%;
    margin-top: 2rem;
    display: flex;
    justify-content: space-between;
    padding: 1.8rem;
    margin-bottom: 30px;
    .page {
      display: flex;
      > :nth-child(1) {
        > img {
          width: 17px;
          height: 17px;
          margin-top: 2px;
        }
      }
      > :nth-child(2) {
        margin-left: 35px;
      }
      .nextbutton {
        > img {
          width: 17px;
          height: 17px;
          margin-top: 2px;
          transform: rotate(180deg);
        }
      }
      .dot {
        padding: 0.5rem 0.8rem;
      }
      button {
        box-shadow: 0;
        background: #fff;
        border-radius: 5px;
        border: none;
        cursor: pointer;
        :hover {
          background-color: #d9d9d9;
        }
      }
      .page-items {
        width: 35px;
        height: 35px;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 100%;
        margin-right: 25px;
        &.check {
          background-color: #1d1d1d;
          color: whitesmoke;
        }
        :not(&.check) {
          cursor: pointer;
        }

        :hover:not(&.check) {
          background-color: #d9d9d9;
          color: black;
        }
      }
    }
  }
`;

function Pagination({ currentPage, currentPageHandler, list }) {
  const pagination = Array(list.totalPages)
    .fill()
    .map((v, i) => i + 1);
  const pagePrevBtn = () => {
    if (currentPage > 1) {
      currentPageHandler(currentPage - 1);
    }
  };

  const pageNextBtn = () => {
    if (currentPage + 1 <= list.totalPages) {
      currentPageHandler(currentPage + 1);
    }
  };

  return (
    <PaginationComponent>
      <div className="pagination">
        <div className="page">
          <button onClick={pagePrevBtn}>
            <img src={prevIcon} alt="페이지이동" className="prev" />
          </button>
          {pagination &&
            pagination.map((v, i) => (
              <button
                className={
                  currentPage === v ? 'page-items check' : 'page-items'
                }
                onClick={() => {
                  currentPageHandler(v);
                }}
                key={(v, i)}
              >
                {v}
              </button>
            ))}
          {/* <div className="dot">...</div> */}
          <button onClick={pageNextBtn} className="nextbutton">
            <img src={prevIcon} alt="페이지이동" className="next" />
          </button>
        </div>
      </div>
    </PaginationComponent>
  );
}

export default Pagination;
