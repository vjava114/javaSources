<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<c:forEach var="dto" items="${lists}" >
			아이디 : ${dto.id}, 이름 : ${dto.name}
			</br>
		</c:forEach> 