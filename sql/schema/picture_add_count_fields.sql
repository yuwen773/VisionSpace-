-- 为 picture 表添加统计计数字段
-- 执行时间: 2026-03-27
-- 说明: 为支持首页推荐系统，需要在 picture 表添加互动计数字段

ALTER TABLE picture
ADD COLUMN IF NOT EXISTS view_count BIGINT DEFAULT 0 COMMENT '浏览次数' AFTER pic_color,
ADD COLUMN IF NOT EXISTS like_count BIGINT DEFAULT 0 COMMENT '点赞次数' AFTER view_count,
ADD COLUMN IF NOT EXISTS collect_count BIGINT DEFAULT 0 COMMENT '收藏次数' AFTER like_count,
ADD COLUMN IF NOT EXISTS download_count BIGINT DEFAULT 0 COMMENT '下载次数' AFTER collect_count,
ADD COLUMN IF NOT EXISTS share_count BIGINT DEFAULT 0 COMMENT '分享次数' AFTER download_count,
ADD COLUMN IF NOT EXISTS impression_count BIGINT DEFAULT 0 COMMENT '曝光次数' AFTER share_count;

-- 添加索引用于推荐查询
ALTER TABLE picture
ADD INDEX idx_picture_stats (review_status, is_delete, view_count),
ADD INDEX idx_picture_like (like_count),
ADD INDEX idx_picture_collect (collect_count);
