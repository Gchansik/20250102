package com.blog.react_spring_blog.repository;

import com.blog.react_spring_blog.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);


}
