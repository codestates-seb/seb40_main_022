import React from 'react';
import styled from 'styled-components';

const PaginationComponent = styled.div`
  .pagination {
    width: 100%;
    margin-top: 2rem;
    display: flex;
    justify-content: space-between;
    padding: 1.8rem;
    .page {
      display: flex;
      .dot {
        padding: 0.5rem 0.8rem;
      }
      button {
        box-shadow: 0;
        border: 1px solid #d9d9d9;
        background: #fff;
        border-radius: 5px;
        :hover {
          background-color: #d9d9d9;
        }
      }
      .page-items {
        border: 1px solid #d9d9d9;
        width: 23px;
        height: 25px;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 5px;
        margin: 3px;
        :not(&.check) {
          cursor: pointer;
        }

        :hover:not(&.check) {
          background-color: #d9d9d9;
        }
      }
    }

    .per {
      display: flex;
      align-items: center;
      .per-items {
        width: 30px;
        height: 25px;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 5px;
        margin-right: 5px;
        border: 1px solid #d9d9d9;
        :not(&.check) {
          cursor: pointer;
        }

        :hover:not(&.check) {
          background-color: #d9d9d9;
        }
      }
    }
    .check {
      background: rgb(244, 130, 37);
      color: #fff;
    }
  }
`;

function Pagination({
  // size,
  currentPage,
  currentPageHandler,
  // paginationLength,
  lanklist,
}) {
  const pagination = Array(lanklist.totalPages)
    .fill()
    .map((v, i) => i + 1);
  const pagePrevBtn = () => {
    if (currentPage > 1) {
      currentPageHandler(currentPage - 1);
    }
  };

  const pageNextBtn = () => {
    if (currentPage + 1 <= lanklist.totalPages) {
      currentPageHandler(currentPage + 1);
    }
  };

  return (
    <PaginationComponent>
      <div className="pagination">
        <div className="page">
          <button onClick={pagePrevBtn}> prev </button>
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
          <button onClick={pageNextBtn}>next</button>
        </div>
      </div>
    </PaginationComponent>
  );
}

export default Pagination;
