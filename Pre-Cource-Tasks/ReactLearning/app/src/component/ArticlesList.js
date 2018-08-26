import Article from "./Article";
import React from 'react'
export default function ArticlesList({articles}) {

    const articleElements = articles.map(article =>
        <Article article={article}/>
    );

    return (
        <ul>{articleElements}</ul>
    )

}